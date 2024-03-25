package com.example.onelab_homework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.homework.book.Book


@Dao
interface BookDao {


  @Query("Select * from book")
  suspend fun getAllBooks():List<BookDbEntity>?


  @Insert
  suspend fun addBook(books:BookDbEntity)



  @Query("Delete from book")
  suspend fun deleteAll()



  @Query("Delete from book where isFavorite =1")
  suspend fun deleteAllFavorites()






  @Query("UPDATE book SET isFavorite =:isFavorite where name =:name")
  suspend fun changeFavorite(name:String ,isFavorite:Boolean)

  @Query("Select * from book where isFavorite =1")
  suspend fun getAllFavorites() :List<BookDbEntity>?






}