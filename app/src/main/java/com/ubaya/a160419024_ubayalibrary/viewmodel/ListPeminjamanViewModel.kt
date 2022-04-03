package com.ubaya.a160419024_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419024_ubayalibrary.model.Peminjaman

class ListPeminjamanViewModel (application: Application) : AndroidViewModel(application){
    val peminjamansLD = MutableLiveData<ArrayList<Peminjaman>>()
    val peminjamanLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        peminjamanLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.fun/native/160419024/daftarpeminjaman.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Peminjaman>>() {}.type
                val result = Gson().fromJson<ArrayList<Peminjaman>>(it, sType)
                peminjamansLD.value = result

                loadingLD.value = false
                Log.d("showvolley", it)
            },{
                peminjamanLoadErrorLD.value = true
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