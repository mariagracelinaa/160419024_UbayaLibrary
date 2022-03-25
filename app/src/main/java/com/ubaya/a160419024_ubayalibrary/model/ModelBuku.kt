package com.ubaya.a160419024_ubayalibrary.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id:String?,
    var judul:String?,
    var penulis:String?,
    var tahun:String?,
    var sinopsis:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)