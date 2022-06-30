package com.ubaya.a160419024_ubayalibrary.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.model.BookDatabase
import java.lang.Exception

@BindingAdapter("imageUrl", "progressBar")
fun loadImageFromUrl(view:ImageView, url:String, pb:ProgressBar){
    view.loadImage(url, pb)
}

fun ImageView.loadImage(url: String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,500)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }
        })
}

val DB_NAME = "bookdb"

fun buildDB(context: Context):BookDatabase{
    val db = Room.databaseBuilder(context, BookDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()

    return db
}

val MIGRATION_1_2 = object : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE 'Ruang' ('uuid'INTEGER,'nama' TEXT, 'kapasitas' TEXT, PRIMARY KEY('uuid'))"
        )
        database.execSQL(
            "CREATE TABLE 'User' ('username' TEXT, 'password' TEXT, PRIMARY KEY('username'))"
        )
    }
}

val MIGRATION_2_3 = object : Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE 'User' ADD COLUMN 'nama' STRING"
        )

        database.execSQL(
            "ALTER TABLE 'User' ADD COLUMN 'photoUrl' STRING"
        )
    }
}