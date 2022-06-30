package com.ubaya.a160419024_ubayalibrary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.databinding.CardBookListBinding
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.util.loadImage
import kotlinx.android.synthetic.main.card_book_list.view.*

class BookListAdapter (val bookList: ArrayList<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>(), BookDetailClickListener{
    class BookViewHolder(var view: CardBookListBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CardBookListBinding.inflate(inflater, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        with(holder.view){
            book = bookList[position]
            detailBookListener = this@BookListAdapter
        }
    }

    override fun getItemCount(): Int = bookList.size

    fun updateBookList(newBookList: List<Book>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onBookDetailClick(view: View) {
        val action = BookListFragmentDirections.actionBookDetail(view.tag as Int)
        Navigation.findNavController(view).navigate(action)
    }
}