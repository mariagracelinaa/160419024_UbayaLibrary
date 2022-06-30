package com.ubaya.a160419024_ubayalibrary.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ruang(
    var nama:String?,
    var kapasitas:String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}