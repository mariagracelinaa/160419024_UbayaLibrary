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
import com.ubaya.a160419024_ubayalibrary.model.Ruang
import com.ubaya.a160419024_ubayalibrary.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RuangViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val ruangsLD = MutableLiveData<List<Ruang>>()
    val ruangLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val job = Job()

    fun refresh() {
        loadingLD.value = false
        ruangLoadErrorLD.value = false
        launch {
            val db = buildDB(getApplication())
            ruangsLD.value = db.ruangDao().selectAllRuang()
        }
    }

    fun addRuang(list:List<Ruang>) {
        launch {
            val db = buildDB(getApplication())
            db.ruangDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}