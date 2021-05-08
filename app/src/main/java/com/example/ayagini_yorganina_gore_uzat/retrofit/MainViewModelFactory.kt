package com.example.ayagini_yorganina_gore_uzat.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val reposiotiry: Reposiotiry):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModelFactory(reposiotiry) as T
    }

}
//class MainViewModelFactory(private val repository: Reposiotiry):ViewModelProvider.Factory{
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return MainViewModelFactory(repository) as T
//    }
//}