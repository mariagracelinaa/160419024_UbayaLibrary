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
import com.ubaya.a160419024_ubayalibrary.databinding.FragmentCreateRuangBinding
import com.ubaya.a160419024_ubayalibrary.databinding.FragmentTambahBukuBinding
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.model.Ruang
import com.ubaya.a160419024_ubayalibrary.viewmodel.DetailBookViewModel
import com.ubaya.a160419024_ubayalibrary.viewmodel.RuangViewModel

class CreateRuangFragment : Fragment(), RuangAddListener {
    private lateinit var viewModel:RuangViewModel
    private lateinit var dataBinding: FragmentCreateRuangBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentCreateRuangBinding.inflate(inflater, container, false)
        return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(RuangViewModel::class.java)

        dataBinding.ruang = Ruang("","")
        dataBinding.addListener = this
    }

    override fun onAddNewRuang(view: View) {
        dataBinding.ruang?.let {
            val list = listOf(it)
            viewModel.addRuang(list)
            Toast.makeText(view.context, "Ruang berhasil ditambah", Toast.LENGTH_LONG).show()
            Navigation.findNavController(view).popBackStack()
        }
    }

}