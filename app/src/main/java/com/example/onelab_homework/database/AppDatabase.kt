package com.example.onelab_homework.database

import androidx.room.Database
import androidx.room.Index
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    version = 1,
    entities = [BookDbEntity::class ]
)
abstract class AppDatabase:RoomDatabase() {

    abstract fun getBookDao():BookDao






}