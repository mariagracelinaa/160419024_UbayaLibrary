package com.ubaya.a160419024_ubayalibrary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.model.Book
import kotlinx.android.synthetic.main.card_book_list.view.*

class BookListAdapter (val bookList: ArrayList<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>(){
    class BookViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_book_list, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        with(holder.view){
            txtJudulBukuCard.text = book.judul
            txtPenulisCard.text = book.penulis
        }
    }

    override fun getItemCount(): Int = bookList.size

    fun updateBookList(newBookList: ArrayList<Book>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }
}