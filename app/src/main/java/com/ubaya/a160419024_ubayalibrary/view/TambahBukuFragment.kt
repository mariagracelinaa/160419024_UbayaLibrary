package com.ubaya.a160419024_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.databinding.FragmentTambahBukuBinding
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.viewmodel.DetailBookViewModel

class TambahBukuFragment : Fragment(), BookAddListener {
    private lateinit var viewModel : DetailBookViewModel
    private lateinit var dataBinding: FragmentTambahBukuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentTambahBukuBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)

        dataBinding.book = Book("","","","","","")
        dataBinding.addListener = this
    }

    override fun onAddNewBook(view: View) {
        dataBinding.book?.let {
            val list = listOf(it)
            viewModel.addBook(list)
            Toast.makeText(view.context, "Buku berhasil ditambah", Toast.LENGTH_LONG).show()
            Navigation.findNavController(view).popBackStack()
        }
    }
}