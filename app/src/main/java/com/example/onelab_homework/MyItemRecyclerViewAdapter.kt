package com.example.onelab_homework

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.MutableBoolean
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework.book.Book
import com.example.onelab_homework.databinding.FragmentItemBinding

class MyItemRecyclerViewAdapter(val navController:NavController ,val context: Context): RecyclerView.Adapter<MyItemRecyclerViewAdapter.SeedsViewHolder>() {
    var itemList =mutableListOf<Book>()
        set(newValue) {
            field= newValue
            notifyDataSetChanged()
        }

    fun assignist(listElement: MutableList<Book>){
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
            //serilizable медленнее, но легче
            bundle.putSerializable("KEY" ,item)
            navController.navigate(R.id.action_itemFragment_to_elementDescriptionPage, bundle)

        }

    }





    class SeedsViewHolder(val binding:FragmentItemBinding):RecyclerView.ViewHolder(binding.root){

    }
}

    data class MyListElement(var name:String ,var text:String )

