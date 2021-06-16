package com.paykey.brsju.kbutils.goroskop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paykey.brsju.kbutils.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class HoroscopeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goroskop)

        val root = findViewById<HoroscopeView>(R.id.root)

        val goroskopApi = HoroscopeApi.client().create(HoroscopeApi::class.java)
        val call: Call<VedicastroapiResponse> = goroskopApi.getDailyHoroscope()

        call.enqueue(object : Callback<VedicastroapiResponse> {

            override fun onResponse(call: Call<VedicastroapiResponse>, responseLocal: Response<VedicastroapiResponse>) {
                val tr: VedicastroapiResponse = responseLocal.body() as VedicastroapiResponse
                Timber.d(
                    " api res = ${tr.response.bot_response.finances.split_response}"
                )
            }

            override fun onFailure(call: Call<VedicastroapiResponse>, t: Throwable) {
                Timber.e(t.message ?: "")
            }
        })
    }
}