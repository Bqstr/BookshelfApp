package com.example.onelab_homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.homework.book.Book
import com.example.onelab_homework.databinding.FragmentElementDescriptionPageBinding

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



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentElementDescriptionPageBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val book =(arguments?.getSerializable("KEY") ?: Book("123","123","123") )as Book
        Glide.with(this)
            .load(book.imageUrl)
            .into(binding.imageView)

        binding.descriptionItem.text =book.descr
        binding.nameItemm.text =book.name



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