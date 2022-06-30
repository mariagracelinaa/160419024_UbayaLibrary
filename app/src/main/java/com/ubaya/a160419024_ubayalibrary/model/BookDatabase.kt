package com.ubaya.a160419024_ubayalibrary.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.a160419024_ubayalibrary.util.MIGRATION_1_2

@Database(entities = arrayOf(Book::class, Ruang::class, User::class), version = 2)
abstract class BookDatabase:RoomDatabase() {
    abstract fun bukuDao():BukuDao
    abstract fun ruangDao():RuangDao
    abstract fun userDao():UserDao

    companion object {
        @Volatile private var instance: BookDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,
            BookDatabase::class.java,
            "bookdb"
        ).fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_1_2)
            .build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}