package com.ubaya.a160419024_ubayalibrary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.model.Jurnal
import com.ubaya.a160419024_ubayalibrary.util.loadImage
import kotlinx.android.synthetic.main.card_book_list.view.*
import kotlinx.android.synthetic.main.card_journal.view.*

class JurnalAdapter(val jurnalList: ArrayList<Jurnal>) : RecyclerView.Adapter<JurnalAdapter.JurnalViewHolder>() {
    class JurnalViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JurnalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_journal, parent, false)
        return JurnalAdapter.JurnalViewHolder(view)
    }

    override fun onBindViewHolder(holder: JurnalAdapter.JurnalViewHolder, position: Int) {
        val jurnal = jurnalList[position]
        with(holder.view){
            txtJudulJurnal.text = jurnal.judul
            txtTanggalJurnal.text = jurnal.tanggal
            txtPenulisJurnal.text = jurnal.penulis
        }
    }

    override fun getItemCount(): Int = jurnalList.size

    fun updateJurnalList(newJurnalList: ArrayList<Jurnal>){
        jurnalList.clear()
        jurnalList.addAll(newJurnalList)
        notifyDataSetChanged()
    }
}