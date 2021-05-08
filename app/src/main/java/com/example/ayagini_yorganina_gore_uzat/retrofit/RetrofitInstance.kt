package com.example.ayagini_yorganina_gore_uzat.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("http://data.fixer.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val api: IApi by lazy {
        retrofit.create(IApi::class.java)
    }
}