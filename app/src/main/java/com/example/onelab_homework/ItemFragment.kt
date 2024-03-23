package com.example.onelab_homework

import android.R.menu
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework.book.Book
import com.example.onelab_homework.databinding.FragmentItemListBinding
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
        adapter = MyItemRecyclerViewAdapter(findNavController(), requireContext() ,App.booksRepository ,lifecycleScope)
        binding.list.adapter =adapter
        binding.list.layoutManager =GridLayoutManager(context, 2)

       // adapter.assignist(sss)

        viewModel.getBooks("night")


       // binding.toobarMainPage.menu

      viewModel.favorites.observe(viewLifecycleOwner){items ->
          val bundle =Bundle()
          bundle.putSerializable("MYKEY" ,items as Serializable)
          findNavController().navigate(R.id.action_itemFragment_to_favoritesFragment, bundle)

      }




        //viewModel.deleteAll()

        viewModel.books.observe(viewLifecycleOwner){
            Log.d("32132123", "observer works")
            adapter.assignist(it)
        }

//        adapter.itemList = listOf{
//            Book("123" ,)
//        }




        val toolbar =binding.toobarMainPage
        toolbar.inflateMenu(R.menu.menu_main_page);
        toolbar.title ="My App"
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
               R.id.Favorites -> {
                   lifecycleScope.launch {
                       val s =viewModel.getAllFavorites()


                      // chtoto(s.await())

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