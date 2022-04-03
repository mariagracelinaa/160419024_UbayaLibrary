package com.ubaya.a160419024_ubayalibrary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.model.Peminjaman
import com.ubaya.a160419024_ubayalibrary.util.loadImage
import kotlinx.android.synthetic.main.card_book_list.view.*
import kotlinx.android.synthetic.main.card_peminjaman.view.*

class ListPeminjamanAdapter (val peminjamanList: ArrayList<Peminjaman>) : RecyclerView.Adapter<ListPeminjamanAdapter.PeminjamanViewHolder>(){
    class PeminjamanViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeminjamanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_peminjaman, parent, false)
        return PeminjamanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeminjamanViewHolder, position: Int) {
        val pinjam = peminjamanList[position]
        with(holder.view){
            txtJudulPeminjaman.text = pinjam.judul
            txtTglPinjam.text = pinjam.tglpinjam
            txtTglKembali.text = pinjam.tglkembali
        }
    }

    override fun getItemCount(): Int = peminjamanList.size

    fun updatePeminjamanList(newPeminjamanList: ArrayList<Peminjaman>){
        peminjamanList.clear()
        peminjamanList.addAll(newPeminjamanList)
        notifyDataSetChanged()
    }
}