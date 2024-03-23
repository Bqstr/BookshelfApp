package com.example.onelab_homework

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.lifecycleScope
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.example.homework.book.Book
import com.example.onelab_homework.databinding.FragmentElementDescriptionPageBinding
import com.example.onelab_homework.databinding.StartTimerBinding
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ElementDescriptionPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class ElementDescriptionPage : Fragment() {

    lateinit var binding:FragmentElementDescriptionPageBinding
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
        binding =FragmentElementDescriptionPageBinding.inflate(layoutInflater)
        val book =(arguments?.getSerializable("KEY") ?: Book(0,"123","123","123" ,false) )as Book
        viewModel.isFavorite.value =book.isFavorite
        name =book.name





//        if(book.isFavorite){
//            val s =binding.toobarDescr.menu.findItem(R.id.addToFavorite)
//            Log.d("asdjkljdsajkdlsajlkads" ,s.toString())
//        }

        Log.d("What" , book.imageUrl)

        Glide.with(this)
            .load(book.imageUrl)
            .into(binding.imageView)

        binding.descriptionItem.text =book.descr
        binding.nameItemm.text =book.name





        val toolbar =binding.toobarDescr
        toolbar.inflateMenu(R.menu.menu_descriprion);
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addToFavorite -> {
                    Log.d("123123","2312231312213123")
                    //it.setIcon(R.drawable.baseline_favorite_24)
                    viewModel.isFavorite.value =!viewModel.isFavorite.value!!


                     true

                }
                R.id.addTimer -> {
                    val builder  = AlertDialog.Builder(context)
                    var binding = StartTimerBinding.inflate(layoutInflater)

                    builder.setView(binding.root)

                    val alertDialog =builder.create()

                    binding.button.setOnClickListener{
                        viewModel.sendMsg(name)

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



        toolbar.title ="My Descr"

        viewModel.isFavorite.observe(viewLifecycleOwner){
            Log.d("123123",it.toString())


            val s = binding.toobarDescr.menu.findItem(R.id.addToFavorite)

            if(it) {
                Log.d("123123","trueasd")

                s.setIcon(R.drawable.baseline_favorite_24)
                viewModel.changeFavorite(name ,isFavorite =it )
            }
            else {
                Log.d("123123","falseasd")

                s.setIcon(R.drawable.baseline_not_favorite)
                viewModel.changeFavorite(name ,isFavorite =it )



            }

        }



        return binding.root
    }







    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ElementDescriptionPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ElementDescriptionPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}