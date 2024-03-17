package com.example.homework.book

import com.example.onelab_homework.book.Resource

interface BooksRepository {
    suspend fun getBooks(text:String): Resource<List<Book>>
}