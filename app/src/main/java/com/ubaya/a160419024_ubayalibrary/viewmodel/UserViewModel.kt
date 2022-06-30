package com.ubaya.a160419024_ubayalibrary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.ubaya.a160419024_ubayalibrary.model.User
import com.ubaya.a160419024_ubayalibrary.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val usersLD = MutableLiveData<User>()

    private val job = Job()

    fun addUser(list:List<User>) {
        launch {
            val db = buildDB(getApplication())
            db.userDao().insertAll(*list.toTypedArray())
        }
    }

    fun fetch() {
        launch {
            val db = buildDB(getApplication())
            usersLD.value = db.userDao().selectProfile()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}