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

class DetailBookViewModel (application: Application) : AndroidViewModel(application) {
    val booksLD = MutableLiveData<Book>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(bookId : String){
        //booksLD.value = Book( "bk001", "Daughter of the deep",  "Rick Riordan", "2021",  "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et justo sollicitudin, scelerisque orci ornare, tristique ligula. Vestibulum et est" ,"https://images-na.ssl-images-amazon.com/images/I/51kwdVrx8zL._SX335_BO1,204,203,200_.jpg")

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/native/160419024/daftarbuku.php?id=$bookId"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<Book>(it, Book::class.java)
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

        bookLoadErrorLD.value = false
        loadingLD.value = false
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}