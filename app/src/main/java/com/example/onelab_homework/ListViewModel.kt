package com.example.onelab_homework

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.book.Book
import com.example.onelab_homework.database.BooksRepository
import com.example.onelab_homework.book.Resource
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ListViewModel(val repository: BooksRepository ,val context: Context): ViewModel() {


    var books = MutableLiveData<MutableList<Book>>()
    var favorites =MutableLiveData<MutableList<Book>>()



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






    suspend fun getAllFavorites()  {
        Log.d("1233312132312" ,"WHAT")
       viewModelScope.launch {
              favorites.value  = repository.getAllFavorites().toMutableList()



       }


    }









}