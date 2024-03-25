package com.example.onelab_homework.database

import android.util.Log
import com.example.homework.book.Book
import com.example.homework.book.BookApi
import com.example.onelab_homework.data.Resource


class BooksRepositoryImpl(val api: BookApi, val bookDao:BookDao): BooksRepository {
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
                val s  = Book(id = 0, imageUrl = "https${sub}",
                    name =ss.items[a].volumeInfo.title,
                   descr = ss.items[a].volumeInfo.description ?: "No description",
                   isFavorite =  false
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

    override suspend fun getBooksFromCache(): List<Book>? {
        val sss =bookDao.getAllBooks()!!.map { BookDbEntity -> BookDbEntity.toBook() }


        return sss
        TODO("Not yet implemented")
    }

    override suspend fun addBookToCache(books: List<Book>) {
        val bookDbEntities = mutableListOf<BookDbEntity>()
        for(a in 0 until books.size){

            bookDbEntities.add(BookDbEntity.toDbEntity(books[a]))

        }

        for(b in 0 until books.size){
            bookDao.addBook(bookDbEntities[b])
        }

        Log.d("32132123", "books.added")



    }

    override suspend fun getFavoritesBook(): List<Book> {
        //return bookDao.getFavoritesBooks().map { BookDbEntity -> BookDbEntity.toBook() }
        return listOf()
    }

    override suspend fun addFavoriteBook(book: Book) {
       // bookDao.addFavorites(BookDbEntity(book.id ,book.name ,book.descr ,book.imageUrl))
    }

    override suspend fun getAllBooks(): List<Book> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllFavorites() {
        bookDao.deleteAllFavorites()
    }

    override suspend fun deleteAllBooks() {
        bookDao.deleteAll()
    }

    override suspend fun changeFavorite(name:String ,isFavorite:Boolean) {
        bookDao.changeFavorite(name,isFavorite)
    }

    override suspend fun getAllFavorites(): List<Book> {
        val books =bookDao.getAllFavorites()
        if(books==null){
            return listOf()
        }
        else{

           return bookDao.getAllFavorites()!!.map { BookDbEntity -> BookDbEntity.toBook()  }

        }
    }

}

