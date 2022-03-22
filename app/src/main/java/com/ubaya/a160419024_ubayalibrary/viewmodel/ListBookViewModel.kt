package com.ubaya.a160419024_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419024_ubayalibrary.model.Book

class ListBookViewModel(application: Application) :AndroidViewModel(application) {
    val booksLD = MutableLiveData<ArrayList<Book>>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh(){
//        booksLD.value = arrayListOf(
//            Book("bk001", "Daughter of the deep",  "Rick Riordan", "2021", "https://picsum.photos/400/500"),
//            Book("bk002", "Semua Bisa Menjadi Programmer Laravel Basic",  "Yuniar Supardi, Sulaeman", "2019", "https://picsum.photos/400/500"),
//            Book("bk003", "Dear Nathan",  "Erisca Febriani", "2016", "https://picsum.photos/400/500"),
//            Book("bk004", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1997", "https://picsum.photos/400/500"),
//            Book("bk005", "The Tower of Nero", "Rick Riordan", "2020", "https://picsum.photos/400/500"),
//            Book("bk006", "Belajar JavaScript Menggunakan JQUERY", "Wahana Komputer",  "2021", "https://picsum.photos/400/500"),
//        )
        bookLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/mariagracelinaa/JSON_UTS/main/daftarbuku.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Book>>() {}.type
                val result = Gson().fromJson<ArrayList<Book>>(it, sType)
                booksLD.value = result

                loadingLD.value = false
                Log.d("showvolley", it)
            },{
                bookLoadErrorLD.value = true
                loadingLD.value = false
                Log.d("error", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}