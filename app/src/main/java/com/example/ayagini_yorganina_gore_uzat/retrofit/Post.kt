package com.example.ayagini_yorganina_gore_uzat.retrofit

import com.google.gson.annotations.SerializedName

data class USD(
        @SerializedName("USD_TRY")
        val USD_PHP : Double
)
data class EUR(
        @SerializedName("EUR_TRY")
        val USD_PHP : Double
)
data class GBP(
        @SerializedName("GBP_TRY")
        val USD_PHP : Double
)

//{
//    val api = "http://data.fixer.io/api/latest?access_key=488be037d986c54455d3f05d055a03b4&format=1"
//}