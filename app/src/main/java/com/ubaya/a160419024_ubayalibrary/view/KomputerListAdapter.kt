package com.ubaya.a160419024_ubayalibrary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.model.Komputer
import kotlinx.android.synthetic.main.card_komputer.view.*
import kotlinx.android.synthetic.main.card_ruangan.view.*

class KomputerListAdapter (val komputerList: ArrayList<Komputer>) : RecyclerView.Adapter<KomputerListAdapter.KomputerViewHolder>(){
    class KomputerViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KomputerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_komputer, parent, false)
        return KomputerListAdapter.KomputerViewHolder(view)
    }

    override fun onBindViewHolder(holder: KomputerViewHolder, position: Int) {
        val komputer = komputerList[position]
        with(holder.view){
            txtKomputer.text = komputer.nama
            txtLokasiKomputer.text = komputer.lokasi
        }
    }

    override fun getItemCount(): Int = komputerList.size

    fun updateKomputerList(newKomputerList: ArrayList<Komputer>){
        komputerList.clear()
        komputerList.addAll(newKomputerList)
        notifyDataSetChanged()
    }
}