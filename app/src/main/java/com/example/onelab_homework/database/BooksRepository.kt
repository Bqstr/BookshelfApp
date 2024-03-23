package com.example.onelab_homework.database

import com.example.homework.book.Book
import com.example.onelab_homework.book.Resource

interface BooksRepository {
    suspend fun getBooks(text:String): Resource<List<Book>>




    suspend fun getBooksFromCache():List<Book>?


    suspend fun addBookToCache(books:List<Book>)



    suspend fun getFavoritesBook():List<Book>

    suspend fun addFavoriteBook(book: Book)


    suspend fun getAllBooks():List<Book>



    //clear cache
    suspend fun deleteAllBooks()


    suspend fun changeFavorite(name:String ,isFavorite:Boolean)

    suspend fun getAllFavorites():List<Book>


}