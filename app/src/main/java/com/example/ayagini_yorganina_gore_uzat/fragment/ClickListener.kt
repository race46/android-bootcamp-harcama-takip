package com.example.ayagini_yorganina_gore_uzat.fragment

import android.view.View
import com.example.ayagini_yorganina_gore_uzat.room_database.Urun

interface ClickListener {
    fun onUrunClicked(v:View,urun: Urun,birim:String)
}