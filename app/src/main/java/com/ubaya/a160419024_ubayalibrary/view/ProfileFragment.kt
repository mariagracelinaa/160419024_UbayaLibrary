package com.ubaya.a160419024_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.databinding.FragmentDetailBukuBinding
import com.ubaya.a160419024_ubayalibrary.databinding.FragmentProfileBinding
import com.ubaya.a160419024_ubayalibrary.util.loadImage
import com.ubaya.a160419024_ubayalibrary.viewmodel.ProfilViewModel
import com.ubaya.a160419024_ubayalibrary.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_detail_buku.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.usersLD.observe(viewLifecycleOwner, {
            dataBinding.user = it
        })
    }
}