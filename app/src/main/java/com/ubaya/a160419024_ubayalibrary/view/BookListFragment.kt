package com.ubaya.a160419024_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.viewmodel.ListBookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookListFragment : Fragment() {
    private lateinit var viewModel : ListBookViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListBookViewModel::class.java)
        viewModel.refresh()

        recViewBookList.layoutManager = GridLayoutManager(context, 3)
        recViewBookList.adapter = bookListAdapter

        refreshLayoutBookList.setOnRefreshListener {
            recViewBookList.visibility = View.GONE
            txtErrorBookList.visibility = View.GONE
            progressLoadBookList.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutBookList.isRefreshing = false
        }

        fabAddBook.setOnClickListener {
            val action = BookListFragmentDirections.actionTambahBuku()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.booksLD.observe(viewLifecycleOwner){
            bookListAdapter.updateBookList(it)
        }
        viewModel.bookLoadErrorLD.observe(viewLifecycleOwner){
            txtErrorBookList.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLD.observe(viewLifecycleOwner){
            if(it){
                recViewBookList.visibility = View.GONE
                progressLoadBookList.visibility = View.VISIBLE
            }else{
                recViewBookList.visibility = View.VISIBLE
                progressLoadBookList.visibility = View.GONE
            }
        }
    }
}