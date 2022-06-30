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

class ListBookViewModel(application: Application) :AndroidViewModel(application), CoroutineScope  {
    val booksLD = MutableLiveData<List<Book>>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

//    fun refresh(){
//        bookLoadErrorLD.value = false
//        loadingLD.value = true
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://ubaya.fun/native/160419024/daftarbuku.php"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<ArrayList<Book>>() {}.type
//                val result = Gson().fromJson<ArrayList<Book>>(it, sType)
//                booksLD.value = result
//
//                loadingLD.value = false
//                Log.d("showvolley", it)
//            },{
//                bookLoadErrorLD.value = true
//                loadingLD.value = false
//                Log.d("error", it.toString())
//            }
//        ).apply {
//            tag = "TAG"
//        }
//        queue?.add(stringRequest)
//    }

    fun refresh() {
        launch {
            val db = Room.databaseBuilder(getApplication(), BookDatabase::class.java, "bookdb").build()
            booksLD.value = db.bukuDao().selectAllBook()

        }
    }

    fun clearTask(book:Book){
        launch {
            val db = buildDB(getApplication())
            db.bukuDao().deleteBook(book)
            booksLD.value = db.bukuDao().selectAllBook()

        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}