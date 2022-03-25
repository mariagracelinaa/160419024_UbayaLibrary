package com.ubaya.a160419024_ubayalibrary.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.util.loadImage
import com.ubaya.a160419024_ubayalibrary.viewmodel.DetailBookViewModel
import kotlinx.android.synthetic.main.fragment_detail_buku.*

class DetailBukuFragment : Fragment() {
    private lateinit var viewModel: DetailBookViewModel
    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_buku, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bookID = DetailBukuFragmentArgs.fromBundle(requireArguments()).bookID
        viewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)
        viewModel.fetch(bookID)

//        arguments?.let {
//            val bookID = DetailBukuFragmentArgs.fromBundle(requireArguments()).bookID
//            queue = Volley.newRequestQueue(context)
//            var url = "https://ubaya.fun/native/160419024/daftarbuku.php?id=$bookID"
//            val stringRequest = StringRequest(
//                Request.Method.GET, url,
//                { response ->
//                    val result = Gson().fromJson<Book>(response, Book::class.java)
//
//                    viewModel.booksLD.value = result
//                    Log.d("showvolley", response.toString())
//                },
//                {
//                    Log.d("showvolley", it.toString())
//                })
//            stringRequest.tag = TAG
//            queue?.add(stringRequest)
//        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.booksLD.observe(viewLifecycleOwner){
            val student = viewModel.booksLD.value
            student?.let {
                imgBukuDetail.loadImage(it.photoUrl, progressBarCoverDetail)
                txtJudulBukuDetail.setText(it.judul)
                txtPenulisDetail.setText(it.penulis)
                txtTahunDetail.setText(it.tahun)
                txtSinopsisDetail.setText(it.sinopsis)
            }
        }
    }
}