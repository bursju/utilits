package com.paykey.brsju.kbutils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface TimezonedbApi {

    @GET("/get-time-zone")
    fun getLocalTimeZone(
        @Query("key") key: String = "GSI7D5KLQ0K4",
        @Query("format") format: String = "json",
        @Query("by") by: String = "zone",
        @Query("zone") zone: String
    ): Call<LocalTimeResponse>

    @GET("list-time-zone")
    fun getAvailableTimeZone(
        @Query("key") key: String = "GSI7D5KLQ0K4",
        @Query("format") format: String = "json"
    ): Call<TimeZonesResponse>

    @GET("convert-time-zone")
    fun convertTime(
        @Query("key") key: String = "GSI7D5KLQ0K4",
        @Query("format") format: String = "json",
        @Query("by") by: String = "zone",
        @Query("from") from: String,
        @Query("to") to: String
    ): Call<ConvertTimeResponse>

    companion object {

        fun client(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client =
                OkHttpClient.Builder().addInterceptor(interceptor).build()

            return Retrofit.Builder()
                .baseUrl("http://api.timezonedb.com/v2.1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}