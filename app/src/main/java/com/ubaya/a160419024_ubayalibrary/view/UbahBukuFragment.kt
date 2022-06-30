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
import com.ubaya.a160419024_ubayalibrary.databinding.FragmentDetailBukuBinding
import com.ubaya.a160419024_ubayalibrary.databinding.FragmentUbahBukuBinding
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.viewmodel.DetailBookViewModel

class UbahBukuFragment : Fragment(), BookSaveChangesListener {
    private lateinit var viewModel: DetailBookViewModel
    private lateinit var dataBinding : FragmentUbahBukuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentUbahBukuBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)
        val bookID = UbahBukuFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(bookID)

        observeViewModel()

        dataBinding.saveListener = this
    }

    private fun observeViewModel() {
        viewModel.booksLD.observe(viewLifecycleOwner) {
            dataBinding.book = it
        }
    }

    override fun onSaveChanges(view: View, obj: Book) {
        viewModel.update(obj.id.toString(), obj.judul.toString(), obj.penulis.toString(), obj.tahun.toString(), obj.sinopsis.toString(), obj.photoUrl.toString(), obj.uuid)
        Toast.makeText(view.context, "Buku Berhasil Diubah", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }
}