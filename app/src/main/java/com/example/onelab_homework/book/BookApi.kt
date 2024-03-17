package com.example.homework.book

import com.example.onelab_homework.book.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface BookApi {
companion object {
     const val BASE_URL = "https://www.googleapis.com/books/v1/"
}
        @GET("volumes")
        suspend fun getBooksByQuery(@Query("q") query: String): BookResponse

}