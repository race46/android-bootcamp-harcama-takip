package com.example.ayagini_yorganina_gore_uzat.room_database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application:Application): AndroidViewModel(application) {
    val readAll: LiveData<List<Urun>>
    private val repository: Repository

    init {
        val userDao = com.example.ayagini_yorganina_gore_uzat.room_database.Database.getDatabase(application).userDao()
        repository = Repository(userDao)
        readAll = repository.readAllData
    }

    fun urunEkle(urun: Urun){
        viewModelScope.launch (Dispatchers.IO){
            repository.urunEkle(urun)
        }
    }
    fun urunSil(urun: Urun){
        viewModelScope.launch (Dispatchers.IO){
            repository.urunSil(urun)
        }
    }



}