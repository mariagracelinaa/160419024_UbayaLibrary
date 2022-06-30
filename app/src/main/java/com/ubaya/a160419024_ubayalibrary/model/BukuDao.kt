package com.ubaya.a160419024_ubayalibrary.model

import androidx.room.*

@Dao
interface BukuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg book:Book)

    @Query("SELECT * FROM book")
    suspend fun selectAllBook(): List<Book>

    @Query("SELECT * FROM book WHERE uuid = :id")
    suspend fun selectBook(id:Int):Book

    @Delete
    suspend fun deleteBook(book:Book)
}