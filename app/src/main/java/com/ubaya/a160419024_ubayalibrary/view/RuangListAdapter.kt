package com.ubaya.a160419024_ubayalibrary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419024_ubayalibrary.R
import com.ubaya.a160419024_ubayalibrary.databinding.CardBookListBinding
import com.ubaya.a160419024_ubayalibrary.databinding.CardRuanganBinding
import com.ubaya.a160419024_ubayalibrary.model.Book
import com.ubaya.a160419024_ubayalibrary.model.Ruang
import kotlinx.android.synthetic.main.card_book_list.view.*
import kotlinx.android.synthetic.main.card_ruangan.view.*

class RuangListAdapter (val ruangList: ArrayList<Ruang>) : RecyclerView.Adapter<RuangListAdapter.RuangViewHolder>(){
    class RuangViewHolder(var view: CardRuanganBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuangViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CardRuanganBinding.inflate(inflater, parent, false)
        return RuangViewHolder(view)
    }

    override fun onBindViewHolder(holder: RuangViewHolder, position: Int) {
        with(holder.view){
            ruangan = ruangList[position]
        }
    }

    override fun getItemCount(): Int = ruangList.size

    fun updateRuangList(newRuangList: List<Ruang>){
        ruangList.clear()
        ruangList.addAll(newRuangList)
        notifyDataSetChanged()
    }
}