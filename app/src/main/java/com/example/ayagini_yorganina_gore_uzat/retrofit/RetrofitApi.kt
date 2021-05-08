package com.example.ayagini_yorganina_gore_uzat.retrofit

import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitApi {
    @GET("api/v7/convert?q=USD_TRY&compact=ultra&apiKey=2019d2bdf98afb3c160d")
    fun getData(): Single<USD>

    @GET("api/v7/convert?q=EUR_TRY&compact=ultra&apiKey=2019d2bdf98afb3c160d")
    fun getDataEur(): Single<EUR>

    @GET("api/v7/convert?q=GBP_TRY&compact=ultra&apiKey=2019d2bdf98afb3c160d")
    fun getDataGbp(): Single<GBP>
}