package com.example.onelab_homework.fragments.description_page

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.fragment.app.Fragment
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.example.homework.book.Book
import com.example.onelab_homework.NotificationWorker

import com.example.onelab_homework.R
import com.example.onelab_homework.databinding.FragmentElementDescriptionPageBinding
import com.example.onelab_homework.databinding.StartTimerBinding
import com.example.onelab_homework.App
import com.example.onelab_homework.viewModelCreator
import java.util.concurrent.TimeUnit


class ElementDescriptionPage : Fragment() {

    lateinit var binding: FragmentElementDescriptionPageBinding
    lateinit var name:String


    val viewModel by viewModelCreator {
        ElementDescriptionViewModel(
            App.booksRepository,
            requireContext()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentElementDescriptionPageBinding.inflate(layoutInflater)
        val book =(arguments?.getSerializable("KEY") ?: Book(0, "123", "123", "123", false))as Book
        viewModel.isFavorite.value =book.isFavorite
        name =book.name







        Log.d("What", book.imageUrl)

        Glide.with(this)
            .load(book.imageUrl)
            .into(binding.imageView)

        binding.descriptionItem.text =book.descr
        binding.nameItemm.text =book.name





        val toolbar =binding.toobarDescr
        val s = Color(0.91764706f, 0.8666667f, 1.0f, 1.0f)
        binding.toobarDescr.setBackgroundColor(s.toArgb())
        toolbar.inflateMenu(R.menu.menu_descriprion);
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addToFavorite -> {
                    Log.d("123123", "2312231312213123")
                    viewModel.isFavorite.value =!viewModel.isFavorite.value!!


                     true

                }
                R.id.addTimer -> {
                    val builder  = AlertDialog.Builder(context)
                    var binding = StartTimerBinding.inflate(layoutInflater)

                    builder.setView(binding.root)

                    val alertDialog =builder.create()

                    binding.button.setOnClickListener{

                        Log.d("12312132213", "${binding.timerTiemHours.text}    ${binding.timerTiemMinutes.text.isBlank()}  ")
                        if( binding.timerTiemHours.text.isBlank() && binding.timerTiemMinutes.text.isBlank()){
                            doWorkImmedeatly(book.name)

                        }
                        else if(!binding.timerTiemHours.text.isBlank() && !binding.timerTiemMinutes.text.isBlank()) {

                             if (binding.switch1.isChecked && validateTime(
                                    binding.timerTiemMinutes,
                                    binding.timerTiemHours
                                )
                            ) {

                                var hours = if(binding.timerTiemHours.text.isBlank()){
                                    "0"
                                }else{
                                     binding.timerTiemHours.text.toString()
                                }

                                 var minutes = if(binding.timerTiemMinutes.text.isBlank()){
                                     "0"
                                 }else{
                                     binding.timerTiemMinutes.text.toString()
                                 }

                                val time: Long = ((hours.toInt() * 60) + minutes.toInt()).toLong()
                                Log.d("check", "periodic")
                                 doWorkPeriodicly(time)


                            } else if (validateTime(
                                    binding.timerTiemMinutes,
                                    binding.timerTiemHours
                                )
                            ) {
                                 var hours = if(binding.timerTiemHours.text.isBlank()){
                                     "0"
                                 }else{
                                     binding.timerTiemHours.text.toString()
                                 }

                                 var minutes = if(binding.timerTiemMinutes.text.isBlank()){
                                     "0"
                                 }else{
                                     binding.timerTiemMinutes.text.toString()
                                 }

                                Log.d("check", "time")

                                 val time: Long = ((hours.toInt() * 60) + minutes.toInt()).toLong()

                                doWorkAfterTime(time)

                            }

                            else{

                                 Toast.makeText(requireContext() ,"Invalid data" ,Toast.LENGTH_SHORT).show()


                            }
                        }
                        else{



                            Toast.makeText(requireContext() ,"Invalid data" ,Toast.LENGTH_SHORT).show()
                        }





                    }


                    binding.button2.setOnClickListener{
                        alertDialog.dismiss()
                    }








                    if(alertDialog.window!=null){
                        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                    }


                    alertDialog.show()


                    true
                    }

                else -> {

                    true
                }


            }
        }



        toolbar.title ="Book Inf"

        viewModel.isFavorite.observe(viewLifecycleOwner){
            Log.d("123123", it.toString())


            val s = binding.toobarDescr.menu.findItem(R.id.addToFavorite)

            if(it) {
                Log.d("123123", "trueasd")

                s.setIcon(R.drawable.baseline_favorite_24)
                viewModel.changeFavorite(name ,isFavorite =it )
            }
            else {
                Log.d("123123", "falseasd")

                s.setIcon(R.drawable.baseline_not_favorite)
                viewModel.changeFavorite(name ,isFavorite =it )



            }

        }



        return binding.root
    }

    fun doWorkImmedeatly(book:String){

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(true)
            .build()

        val data = Data.Builder()
        data.putString("HEREKEY", book)

        val work_request  = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data.build())
            .build()

        WorkManager.getInstance(requireContext()).enqueue(work_request)

    }

    private fun doWorkAfterTime(minutes:Long) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val myRequest = PeriodicWorkRequest.Builder(
            NotificationWorker::class.java,
            minutes,
            TimeUnit.MINUTES
        ).setConstraints(constraints)
            .addTag("my_id")
            .build()



        WorkManager.getInstance(requireContext())
            .enqueueUniquePeriodicWork(
                "my_id",
                ExistingPeriodicWorkPolicy.KEEP,
                myRequest
            )
    }



    private fun doWorkPeriodicly(minutes: Long){


        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val myRequest = PeriodicWorkRequest.Builder(
            NotificationWorker::class.java,
            minutes,
            TimeUnit.MINUTES,
            minutes,
            TimeUnit.MINUTES
        ).setConstraints(constraints)
            .addTag("my_id")
            .build()



        WorkManager.getInstance(requireContext())
            .enqueueUniquePeriodicWork(
                "my_id",
                ExistingPeriodicWorkPolicy.KEEP,
                myRequest
            )

    }

    private fun validateTime(minutes :EditText,hours:EditText):Boolean{
        Log.d("fdgddggfdf","${minutes.text.toString().toInt()>=15}")


        var hourss =hours.text.toString()

        Log.d("fdgddggfdf","${(hours.text.isBlank() || hours.text.equals("0"))}")
        return (((hourss=="0")  && (minutes.text.toString().toInt()>=15 )) ||
        hours.text.toString().toInt() >=1)
    }








}