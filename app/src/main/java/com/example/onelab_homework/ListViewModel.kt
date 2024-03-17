package com.example.onelab_homework

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.book.Book
import com.example.homework.book.BooksRepository
import com.example.onelab_homework.book.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ListViewModel(val repository: BooksRepository): ViewModel() {


    var books = MutableLiveData<MutableList<Book>>()

    fun getBooks(text:String){
        viewModelScope.launch {
            val res =repository.getBooks(text)

            when (res) {
                is Resource.Success -> {
                    Log.d("12132321132" , res.data?.get(0)!!.imageUrl.toString())
                    books.value =res.data.toMutableList()

                }

                is Resource.Loading -> {
                    Log.d("12132321132","Loading")

                }

                is Resource.Error -> {
                    Log.d("12132321132","Errorrrr")
                }
            }
        }


    }




}