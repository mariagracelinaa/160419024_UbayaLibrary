package com.ubaya.a160419024_ubayalibrary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.model.Ruang
import kotlinx.android.synthetic.main.card_book_list.view.*
import kotlinx.android.synthetic.main.card_ruangan.view.*

class RuangListAdapter (val ruangList: ArrayList<Ruang>) : RecyclerView.Adapter<RuangListAdapter.RuangViewHolder>(){
    class RuangViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuangViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_ruangan, parent, false)
        return RuangListAdapter.RuangViewHolder(view)
    }

    override fun onBindViewHolder(holder: RuangViewHolder, position: Int) {
        val ruang = ruangList[position]
        with(holder.view){
            txtNamaRuang.text = ruang.nama
            txtKapasitasRuang.text = ruang.kapasitas
        }
    }

    override fun getItemCount(): Int = ruangList.size

    fun updateRuangList(newRuangList: ArrayList<Ruang>){
        ruangList.clear()
        ruangList.addAll(newRuangList)
        notifyDataSetChanged()
    }
}