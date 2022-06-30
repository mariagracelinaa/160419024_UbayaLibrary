package com.ubaya.a160419024_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.databinding.FragmentRegisterBinding
import com.ubaya.a160419024_ubayalibrary.databinding.FragmentTambahBukuBinding
import com.ubaya.a160419024_ubayalibrary.model.User
import com.ubaya.a160419024_ubayalibrary.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(), RegisterListener {
    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        dataBinding.user = User("", "")
        dataBinding.registerListener = this

        txtClickHereToLogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionToLogin()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onRegisterUser(view: View) {
        dataBinding.user?.let {
            val list = listOf(it)
            viewModel.addUser(list)

            Toast.makeText(view.context, "User berhasil ditambah", Toast.LENGTH_LONG).show()
            Navigation.findNavController(view).popBackStack()
        }
    }
}