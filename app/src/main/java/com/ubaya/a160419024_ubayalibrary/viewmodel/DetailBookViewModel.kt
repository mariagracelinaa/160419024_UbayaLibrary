package com.ubaya.a160419024_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.model.BookDatabase
import com.ubaya.a160419024_ubayalibrary.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailBookViewModel (application: Application) : AndroidViewModel(application), CoroutineScope {
    val booksLD = MutableLiveData<Book>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun addBook(list:List<Book>) {
        launch {
            val db = buildDB(getApplication())
            db.bukuDao().insertAll(*list.toTypedArray())
        }
    }

    private val job = Job()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(id : Int){
        launch {
            val db = buildDB(getApplication())
            booksLD.value = db.bukuDao().selectBook(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun update(id: String, judul: String, penulis: String, tahun: String, sinopsis: String, photoUrl: String, uuid: Int){
        launch {
            val db = buildDB(getApplication())
            db.bukuDao().update(id, judul, penulis, tahun, sinopsis, photoUrl, uuid)
        }
    }

    fun clearTask(book: Book) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                BookDatabase::class.java, "bookdb").build()
            db.bukuDao().deleteBook(book)
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}