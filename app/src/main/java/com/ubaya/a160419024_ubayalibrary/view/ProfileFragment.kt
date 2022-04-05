package com.ubaya.a160419024_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.util.loadImage
import com.ubaya.a160419024_ubayalibrary.viewmodel.DetailBookViewModel
import com.ubaya.a160419024_ubayalibrary.viewmodel.ProfilViewModel
import kotlinx.android.synthetic.main.fragment_detail_buku.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.profilsLD.observe(viewLifecycleOwner){
            val profil = viewModel.profilsLD.value
            profil?.let {
                imgProfil.loadImage(it.url, progressBarProfil)
                txtNamaProfil.setText(it.nama)
                txtNRPProfil.setText(it.nrp)
                txtFakultas.setText(it.fakultas)
                txtJurusan.setText(it.jurusan)
                txtEmail.setText(it.email)
            }
        }
    }
}