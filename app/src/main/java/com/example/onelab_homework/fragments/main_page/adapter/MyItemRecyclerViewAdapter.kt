package com.example.onelab_homework.fragments.main_page.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework.book.Book
import com.example.onelab_homework.R
import com.example.onelab_homework.database.BooksRepository
import com.example.onelab_homework.databinding.FragmentItemBinding
import kotlinx.coroutines.launch

class MyItemRecyclerViewAdapter(
    val navController: NavController,
    val context: Context,
    val booksRepository: BooksRepository,
    val lifecycleScope: LifecycleCoroutineScope
): RecyclerView.Adapter<MyItemRecyclerViewAdapter.SeedsViewHolder>() {
    var itemList =mutableListOf<Book>()
        set(newValue) {
            field= newValue
            notifyDataSetChanged()
        }

    fun assignist(listElement: MutableList<Book>){


        for(a in listElement){
        }
        itemList =listElement
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeedsViewHolder {

        val inflater =LayoutInflater.from(parent.context)
        val binding =FragmentItemBinding.inflate(inflater,parent,false)
        return SeedsViewHolder(binding)
    }




    override fun getItemCount()= itemList.size





    override fun onBindViewHolder(holder: SeedsViewHolder, position: Int) {
        val item =itemList[position]




        Glide.with(context)
            .load(item.imageUrl)
            .into(holder.binding.itemImage)



        holder.binding.root.setOnClickListener{
            val bundle =Bundle()
            bundle.putSerializable("KEY" ,item)
            navController.navigate(R.id.action_itemFragment_to_elementDescriptionPage, bundle)
        }

        holder.binding.root.setOnLongClickListener{
            lifecycleScope.launch {
                booksRepository.addFavoriteBook(item)
                Toast.makeText(context ,"Added!",Toast.LENGTH_SHORT).show()
            }

            true
        }

    }





    class SeedsViewHolder(val binding:FragmentItemBinding):RecyclerView.ViewHolder(binding.root){

    }
}

    data class MyListElement(var name:String ,var text:String )

