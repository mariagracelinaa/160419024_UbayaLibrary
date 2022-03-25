package com.ubaya.a160419024_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.viewmodel.KomputerViewModel
import com.ubaya.a160419024_ubayalibrary.viewmodel.RuangViewModel
import kotlinx.android.synthetic.main.fragment_komputer.*
import kotlinx.android.synthetic.main.fragment_ruang.*

class KomputerFragment : Fragment() {
    private lateinit var viewModel : KomputerViewModel
    private val komputerListAdapter = KomputerListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_komputer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(KomputerViewModel::class.java)
        viewModel.refresh()

        recViewKomputer.layoutManager = LinearLayoutManager(context)
        recViewKomputer.adapter = komputerListAdapter

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
        viewModel.komputersLD.observe(viewLifecycleOwner){
            komputerListAdapter.updateKomputerList(it)
        }
        viewModel.komputerLoadErrorLD.observe(viewLifecycleOwner){
            txtErrorKomputer.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLD.observe(viewLifecycleOwner){
            if(it){
                recViewKomputer.visibility = View.GONE
                progressBarKomputer.visibility = View.VISIBLE
            }else{
                recViewKomputer.visibility = View.VISIBLE
                progressBarKomputer.visibility = View.GONE
            }
        }
    }
}