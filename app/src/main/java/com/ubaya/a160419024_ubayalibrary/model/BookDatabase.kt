package com.ubaya.a160419024_ubayalibrary.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Book::class), version = 1)
abstract class BookDatabase:RoomDatabase() {
    abstract fun bukuDao():BukuDao

    companion object {
        @Volatile private var instance: BookDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,
            BookDatabase::class.java,
            "bookdb"
        ).build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}