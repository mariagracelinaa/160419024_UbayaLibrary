package com.ubaya.a160419024_ubayalibrary.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.databinding.FragmentDetailBukuBinding
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.util.loadImage
import com.ubaya.a160419024_ubayalibrary.viewmodel.DetailBookViewModel
import kotlinx.android.synthetic.main.card_book_list.view.*
import kotlinx.android.synthetic.main.fragment_detail_buku.*
import kotlinx.android.synthetic.main.fragment_ubah_buku.*

class DetailBukuFragment : Fragment() {
    private lateinit var viewModel: DetailBookViewModel
    private lateinit var dataBinding : FragmentDetailBukuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentDetailBukuBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)
        val bookID = DetailBukuFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(bookID)


        btnUbah.setOnClickListener {
            val ID = DetailBukuFragmentArgs.fromBundle(requireArguments()).uuid
            val action = DetailBukuFragmentDirections.actionEditBuku(ID)
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.booksLD.observe(viewLifecycleOwner, {
            dataBinding.book = it
        })
    }
}