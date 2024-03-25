package com.example.onelab_homework.fragments.main_page

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.book.Book
import com.example.onelab_homework.database.BooksRepository
import com.example.onelab_homework.data.Resource
import com.example.onelab_homework.util.SingleLiveEvent
import kotlinx.coroutines.launch

class ListViewModel(val repository: BooksRepository ,val context: Context): ViewModel() {


    var books = MutableLiveData<MutableList<Book>>()



    fun getBooks(text:String){



        viewModelScope.launch {

            val bookss = repository.getBooksFromCache()!!
            val comp = listOf<Book>()
            if (bookss.size!=0  ) {
                Log.d("32132123",bookss.size.toString())
                books.value = bookss.toMutableList()


            } else {
                Log.d("32132123","HERE HH")

                val res = repository.getBooks(text)

                when (res) {
                    is Resource.Success -> {
                        Log.d("12132321132", res.data?.get(0)!!.imageUrl.toString())
                        books.value = res.data.toMutableList()
                        repository.addBookToCache(res.data)

                    }

                    is Resource.Loading -> {
                        Log.d("12132321132", "Loading")

                    }

                    is Resource.Error -> {


                        Log.d("12132321132", "Errorrrr")
                    }
                }
            }
        }


    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAllBooks()
        }
    }
















}