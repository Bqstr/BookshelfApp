package com.example.onelab_homework

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onelab_homework.databinding.FragmentItemBinding
import com.example.onelab_homework.databinding.FragmentItemListBinding
import com.example.onelab_homework.placeholder.PlaceholderContent
import kotlinx.coroutines.flow.observeOn

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {
    val viewModel by viewModelCreator {
        ListViewModel(
            App.booksRepository
        )
    }



   lateinit var binding : FragmentItemListBinding
   lateinit var adapter: MyItemRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentItemListBinding.inflate(layoutInflater)
        adapter = MyItemRecyclerViewAdapter(findNavController(), requireContext())
        binding.list.adapter =adapter
        binding.list.layoutManager =GridLayoutManager(context, 2)

       // adapter.assignist(sss)

        viewModel.getBooks("night")






        viewModel.books.observe(viewLifecycleOwner){
            adapter.assignist(it)
        }



        return binding.root
    }


}