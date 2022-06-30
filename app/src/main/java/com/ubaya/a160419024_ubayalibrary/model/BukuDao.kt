package com.ubaya.a160419024_ubayalibrary.model

import androidx.room.*

@Dao
interface BukuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg book:Book)

    @Query("SELECT * FROM Book")
    suspend fun selectAllBook(): List<Book>

    @Query("SELECT * FROM Book WHERE uuid = :id")
    suspend fun selectBook(id:Int):Book

    @Query("UPDATE Book SET id = :id, judul = :judul, penulis = :penulis, tahun = :tahun, sinopsis = :sinopsis, photoUrl = :photoUrl WHERE uuid = :uuid")
    suspend fun update(id: String, judul: String, penulis: String, tahun: String, sinopsis: String, photoUrl: String, uuid: Int)

    @Delete
    suspend fun deleteBook(book:Book)
}