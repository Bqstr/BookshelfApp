package com.example.homework.book

import android.util.Log
import com.example.onelab_homework.book.Resource

class BooksRepositoryImpl(val api: BookApi): BooksRepository {
    override suspend  fun getBooks(text:String): Resource<List<Book>> {



        val response = try {

            val ss =api.getBooksByQuery(text)
            val resp = mutableListOf<Book>()
            Log.d("12132321132","${ss.toString()}  ")
            Log.d("12132321132","${ss.items.size}  ")
            ss.items[0].volumeInfo.title
            ss.items[0].volumeInfo.description


            for(a in 0 until 8){
               Log.d("12132321132" ,ss.items.get(a).volumeInfo.imageLinks.smallThumbnail)
                Log.d("12132321132" ,ss.items.get(a).volumeInfo.title)
                Log.d("12132321132" ,ss.items.get(a).volumeInfo.description ?: "No descr")

                val sub =ss.items.get(a).volumeInfo.imageLinks.smallThumbnail.substring(4)
                val s  = Book( "https${sub}",
                    ss.items[a].volumeInfo.title,
                    ss.items[a].volumeInfo.description ?: "No description"
                    )
                resp.add(s)
            }
            Log.d("12132321132","what")
            return Resource.Success(resp)

        } catch(e: Exception) {
            Log.d("12132321132" ,e.toString())
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Loading()










//
//
//        call.enqueue(object : Callback<BookResponse> {
//            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
//                if (response.isSuccessful) {
//                    val bookResponse = response.body()
//                    response
//                    for(a in 0 until bookResponse!!.items.size){
//                        listOfBook.add(Book(
//                            bookResponse.items.get(a).volumeInfo.imageLinks.thumbnail
//                        )
//
//                        )
//                    }
//                } else {
//                    Log.d("LOGGING", "ERROR")
//                    // Handle error
//                }
//            }
//
//            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
//                Log.d("LOGGING", "Failiare")
//            }
//        })
//        return listOfBook
    }

    }

