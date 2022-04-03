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
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.model.Profil

class ProfilViewModel(application: Application) : AndroidViewModel(application) {
    val profilsLD = MutableLiveData<Profil>()
    val profilLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(){
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/native/160419024/profil.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<Profil>(it, Profil::class.java)
                profilsLD.value = result

                loadingLD.value = false
                Log.d("showvolley", it)
            },{
                profilLoadErrorLD.value = true
                loadingLD.value = false
                Log.d("error", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)

        profilLoadErrorLD.value = false
        loadingLD.value = false
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}