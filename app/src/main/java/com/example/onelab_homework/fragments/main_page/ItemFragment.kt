package com.example.onelab_homework.fragments.main_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onelab_homework.fragments.main_page.adapter.MyItemRecyclerViewAdapter
import com.example.onelab_homework.R
import com.example.onelab_homework.databinding.FragmentItemListBinding
import com.example.onelab_homework.App
import com.example.onelab_homework.viewModelCreator
import kotlinx.coroutines.launch
import java.io.Serializable


/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {
    val viewModel by viewModelCreator {
        ListViewModel(
            App.booksRepository,
            requireContext()

        )
    }



   lateinit var binding : FragmentItemListBinding
   lateinit var adapter: MyItemRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentItemListBinding.inflate(layoutInflater)
        adapter = MyItemRecyclerViewAdapter(findNavController(), requireContext() ,
            App.booksRepository ,lifecycleScope)
        binding.list.adapter =adapter
        binding.list.layoutManager =GridLayoutManager(context, 2)


        viewModel.getBooks("night")









        viewModel.books.observe(viewLifecycleOwner){
            Log.d("32132123", "observer works")
            adapter.assignist(it)
        }





        val toolbar =binding.toobarMainPage

        val s = Color(0.91764706f, 0.8666667f, 1.0f, 1.0f)
        binding.toobarMainPage.setBackgroundColor(s.toArgb())
        toolbar.inflateMenu(R.menu.menu_main_page);
        toolbar.title ="My App"


        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
               R.id.Favorites -> {
                   lifecycleScope.launch {
                       findNavController().navigate(R.id.action_itemFragment_to_favoritesFragment)


                   }
                       true

               }



                else -> {
                    false
                }
            }
        }









        return binding.root
    }











}