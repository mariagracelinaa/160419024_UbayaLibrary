package com.ubaya.a160419024_ubayalibrary.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var username:String?,
    var password:String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}