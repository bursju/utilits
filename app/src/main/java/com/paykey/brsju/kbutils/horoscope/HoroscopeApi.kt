package com.paykey.brsju.kbutils.horoscope

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface HoroscopeApi {

    @GET("prediction/dailysun")
    fun getDailyHoroscope(
        @Query("zodiac") zodiac: Int = 11,
        @Query("type") type: String = "big",
        @Query("api_key") api_key: String = "e93a3e6b-29ad-544c-aadf-d1ff701c85b7",
        @Query("show_same") show_same: Boolean = true,
        @Query("split") split: Boolean = true,
        @Query("date") date: String = "17/06/2021"//todo make for current day
    ): Call<VedicastroapiResponse<HoroscopeResponse>>

    companion object {

        fun client(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl("https://api.vedicastroapi.com/json/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}