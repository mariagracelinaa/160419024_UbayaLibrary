package com.ubaya.a160419024_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.viewmodel.ListBookViewModel
import com.ubaya.a160419024_ubayalibrary.viewmodel.RuangViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_ruang.*

class RuangFragment : Fragment() {
    private lateinit var viewModel : RuangViewModel
    private val ruangListAdapter = RuangListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ruang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(RuangViewModel::class.java)
        viewModel.refresh()

        recViewRuang.layoutManager = LinearLayoutManager(context)
        recViewRuang.adapter = ruangListAdapter

//        refreshLayoutRuangList.setOnRefreshListener {
//            recViewRuang.visibility = View.GONE
//            txtErrorDaftarRuang.visibility = View.GONE
//            progressBarDaftarRuang.visibility = View.VISIBLE
//            viewModel.refresh()
//            refreshLayoutRuangList.isRefreshing = false
//        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.ruangsLD.observe(viewLifecycleOwner){
            ruangListAdapter.updateRuangList(it)
        }
        viewModel.ruangLoadErrorLD.observe(viewLifecycleOwner){
            txtErrorDaftarRuang.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLD.observe(viewLifecycleOwner){
            if(it){
                recViewRuang.visibility = View.GONE
                progressBarDaftarRuang.visibility = View.VISIBLE
            }else{
                recViewRuang.visibility = View.VISIBLE
                progressBarDaftarRuang.visibility = View.GONE
            }
        }
    }
}