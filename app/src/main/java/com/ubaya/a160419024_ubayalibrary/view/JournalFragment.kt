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
import com.ubaya.a160419024_ubayalibrary.viewmodel.JurnalViewModel
import com.ubaya.a160419024_ubayalibrary.viewmodel.ListBookViewModel
import com.ubaya.a160419024_ubayalibrary.viewmodel.RuangViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_journal.*

class JournalFragment : Fragment() {
    private lateinit var viewModel : JurnalViewModel
    private val jurnalListAdapter = JurnalAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_journal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(JurnalViewModel::class.java)
        viewModel.refresh()

        recViewJurnal.layoutManager = LinearLayoutManager(context)
        recViewJurnal.adapter = jurnalListAdapter

//        refreshLayoutBookList.setOnRefreshListener {
//            recViewJurnal.visibility = View.GONE
//            txtErrorJurnal.visibility = View.GONE
//            progressBarDaftarJurnal.visibility = View.VISIBLE
//            viewModel.refresh()
//            refreshLayoutBookList.isRefreshing = false
//        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.jurnalsLD.observe(viewLifecycleOwner){
            jurnalListAdapter.updateJurnalList(it)
        }
        viewModel.jurnalLoadErrorLD.observe(viewLifecycleOwner){
            txtErrorJurnal.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLD.observe(viewLifecycleOwner){
            if(it){
                recViewJurnal.visibility = View.GONE
                progressBarDaftarJurnal.visibility = View.VISIBLE
            }else{
                recViewJurnal.visibility = View.VISIBLE
                progressBarDaftarJurnal.visibility = View.GONE
            }
        }
    }
}