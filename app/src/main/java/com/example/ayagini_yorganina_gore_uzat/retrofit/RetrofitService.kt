package com.example.ayagini_yorganina_gore_uzat.retrofit

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private val BASE_URL = "https://free.currconv.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RetrofitApi::class.java)

    fun getData() : Single<USD>{
        return api.getData()
    }

    fun getDataEur() : Single<EUR>{
        return api.getDataEur()
    }

    fun getDataGbp() : Single<GBP> {
        return api.getDataGbp()
    }
}