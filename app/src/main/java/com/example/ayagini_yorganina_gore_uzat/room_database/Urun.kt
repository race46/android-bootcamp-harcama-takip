package com.example.ayagini_yorganina_gore_uzat.room_database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.recycler_view_row.view.*
import kotlin.math.roundToInt

@Parcelize
@Entity(tableName = "urun_listesi")
data class Urun(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var aciklama: String,
    val tutar: Float,
    val tip: String,
    val birim: String
):Parcelable{
    fun getConverterCurrency(birim: String,usd:Float,eur:Float,gbp:Float):String{
        var _tutar = tutar
        var item_birim = this.birim
        when(item_birim){
            "dolar" -> _tutar *= usd
            "euro" -> _tutar *= eur
            "sterlin" -> _tutar *= gbp
        }
        when(birim){
            "Dolar" -> _tutar /= usd
            "Euro" -> _tutar /= eur
            "Sterlin" -> _tutar /= gbp
        }

        //return this.tutar.toString()+":"+this.birim+"--"+_tutar.toInt().toString()+":"+birim

        return ((_tutar*100).roundToInt()/100.0).toString() + " " + birim

    }
}