package com.example.onelab_homework

import android.content.Context
import androidx.room.Room
import com.example.homework.book.BookApi
import com.example.onelab_homework.database.BooksRepository
import com.example.onelab_homework.database.AppDatabase
import com.example.onelab_homework.database.BooksRepositoryImpl
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





    private val database1: AppDatabase by lazy<AppDatabase> {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            //      .createFromAsset("initial_database.db")
            .build()
    }



    private val bookApi = retrofit.create(BookApi::class.java)


    val booksRepository: BooksRepository by lazy{
        BooksRepositoryImpl(bookApi, database1.getBookDao() )
    }





}
