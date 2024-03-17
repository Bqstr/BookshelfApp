package com.example.onelab_homework

import android.content.Context
import com.example.homework.book.BookApi
import com.example.homework.book.BooksRepository
import com.example.homework.book.BooksRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object App {

    lateinit var applicationContext: Context
    fun init(context:Context){
        applicationContext =context
    }
    val retrofit = Retrofit.Builder()
        .baseUrl(BookApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()




    private val bookApi = retrofit.create(BookApi::class.java)


    val booksRepository: BooksRepository by lazy{
        BooksRepositoryImpl(bookApi )
    }



}
