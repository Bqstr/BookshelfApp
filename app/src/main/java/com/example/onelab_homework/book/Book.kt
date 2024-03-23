package com.example.homework.book

import com.example.onelab_homework.book.SeriesInfo
import java.io.Serializable

data class Book(var id:Long ,var name:String ,var descr :String ,var imageUrl :String, var isFavorite:Boolean):Serializable
