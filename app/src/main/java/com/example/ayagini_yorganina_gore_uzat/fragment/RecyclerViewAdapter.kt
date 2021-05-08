package com.example.ayagini_yorganina_gore_uzat.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ayagini_yorganina_gore_uzat.R
import com.example.ayagini_yorganina_gore_uzat.databinding.RecyclerViewRowBinding
import com.example.ayagini_yorganina_gore_uzat.room_database.Urun
import kotlinx.android.synthetic.main.recycler_view_row.view.*
import kotlin.math.E
import kotlin.math.roundToInt

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.RowTasarim>() ,ClickListener{

    private var urunList = emptyList<Urun>()
    private var birim = "Tl"
    var USD = 0.0f
    var EUR = 0.0f
    var GBP = 0.0f

    class RowTasarim(var view: RecyclerViewRowBinding):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowTasarim {
        //return RowTasarim(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row,parent,false))
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewRowBinding>(inflater,R.layout.recycler_view_row,parent,false)
        return RowTasarim(view)
    }

    override fun getItemCount(): Int {
        return urunList.size
    }

    override fun onBindViewHolder(holder: RowTasarim, position: Int) {
        holder.view.urun = urunList[position]
        holder.view.currency = urunList[position].getConverterCurrency(birim,USD,EUR,GBP)
        holder.itemView.imageView2.setImageResource(urunList[position].tip.toInt())
        holder.view.listener = this


    }
    fun setData(urun: List<Urun>,birim:String,usd:Float,eur:Float,gbp:Float){
        this.urunList = urun
        this.birim = birim
        this.USD = usd
        this.EUR = eur
        this.GBP = gbp
        notifyDataSetChanged()
    }
    fun changeCurrency(birim: String,usd:Float,eur:Float,gbp:Float){
        this.birim = birim
        this.USD = usd
        this.EUR = eur
        this.GBP = gbp
        notifyDataSetChanged()
    }

    override fun onUrunClicked(v: View, urun: Urun, birim: String) {
        val action = GirisDirections.actionGirisToDetay(urun,birim,urun.tutar)
        Navigation.findNavController(v).navigate(action)
    }


}