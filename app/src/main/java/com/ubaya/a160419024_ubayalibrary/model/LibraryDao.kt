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


@Dao
interface RuangDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg ruang: Ruang)

    @Query("SELECT * FROM Ruang")
    suspend fun selectAllRuang(): List<Ruang>

    @Query("SELECT * FROM Ruang WHERE uuid = :id")
    suspend fun selectRuang(id:Int):Ruang

    @Query("UPDATE Ruang SET nama= :nama, kapasitas= :kapasitas WHERE uuid = :uuid")
    suspend fun updateRuang(nama:String, kapasitas:String, uuid:Int)

    @Delete
    suspend fun deleteRuang(book:Book)
}


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: User)

    @Query("SELECT * FROM User LIMIT 1")
    suspend fun selectProfile(): User

    @Query("SELECT * FROM User WHERE username= :username AND password= :password")
    suspend fun selectUser(username:String, password:String):User

    @Query("UPDATE User SET password= :password WHERE username = :username")
    suspend fun updateUser(password: String, username: String)

    @Delete
    suspend fun deleteUser(user:User)
}