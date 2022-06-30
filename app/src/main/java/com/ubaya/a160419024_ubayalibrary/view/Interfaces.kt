package com.ubaya.a160419024_ubayalibrary.view

import android.view.View
import com.ubaya.a160419024_ubayalibrary.model.Book

interface BookAddListener{
    fun onAddNewBook(view: View)
}

interface BookDetailClickListener {
    fun onBookDetailClick(view: View)
}

interface BookSaveChangesListener{
    fun onSaveChanges(
        view :View,
        obj:Book
    )
}

interface RuangAddListener{
    fun onAddNewRuang(view: View)
}

interface RegisterListener {
    fun onRegisterUser(view: View)
}