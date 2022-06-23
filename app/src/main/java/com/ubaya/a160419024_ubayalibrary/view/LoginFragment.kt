package com.ubaya.a160419024_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.a160419024_ubayalibrary.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtClickHereToRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionRegister()
            Navigation.findNavController(it).navigate(action)
        }

        btnLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionItemBuku()
            Navigation.findNavController(it).navigate(action)
        }

    }
}