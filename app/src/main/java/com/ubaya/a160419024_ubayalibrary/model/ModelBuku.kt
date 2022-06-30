package com.ubaya.a160419024_ubayalibrary.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Book(
    var id:String?,
    var judul:String?,
    var penulis:String?,
    var tahun:String?,
    var sinopsis:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}