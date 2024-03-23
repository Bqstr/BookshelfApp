package com.example.onelab_homework.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.homework.book.Book

import java.util.PriorityQueue

@Entity(tableName = "book")
public class BookDbEntity(


    @PrimaryKey(autoGenerate = true)  val  id:Long,
    @ColumnInfo(name ="name") val name:String,
    @ColumnInfo(name ="description") var descr :String,
    @ColumnInfo(name ="price") var imageUrl:String ,
    @ColumnInfo(name ="isFavorite") var isFavorite:Boolean


    ) {


    fun toBook(): Book {
        BookDbEntity
        return Book(
            id =id,
            name =name,
            descr =descr,
            imageUrl =imageUrl,
           isFavorite= isFavorite
        )
    }



    companion object {

        fun toDbEntity(book: Book): BookDbEntity {

            return BookDbEntity(
                name = book.name,
                descr = book.descr,
                imageUrl = book.imageUrl,
                id = book.id,
                isFavorite = book.isFavorite
            )
        }

        fun toBook(bookDbEntity: BookDbEntity): Book {
            return Book(
               id = bookDbEntity.id,
               name = bookDbEntity.name,
               descr = bookDbEntity.descr,
                imageUrl =bookDbEntity.imageUrl,
                isFavorite =bookDbEntity.isFavorite
            )
        }
    }



}