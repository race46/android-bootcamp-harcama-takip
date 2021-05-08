package com.example.ayagini_yorganina_gore_uzat.room_database

import androidx.lifecycle.LiveData


class Repository(private val userDao: Dao) {
    val readAllData: LiveData<List<Urun>> = userDao.urunleriAl()

    suspend fun urunEkle(urun: Urun){
        userDao.ekleUrun(urun)
    }
    suspend fun urunSil(urun: Urun){
        userDao.silUrun(urun)
    }

}