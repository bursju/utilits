package com.paykey.brsju.kbutils.goroskop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paykey.brsju.kbutils.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class GoroskopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goroskop)

        val root = findViewById<GoroskopView>(R.id.root)

        val goroskopApi = GoroskopApi.client().create(GoroskopApi::class.java)
        val call: Call<GoroskopResponse> = goroskopApi.getGoroskop()

        call.enqueue(object : Callback<GoroskopResponse> {

            override fun onResponse(call: Call<GoroskopResponse>, responseLocal: Response<GoroskopResponse>) {
                val tr: GoroskopResponse = responseLocal.body() as GoroskopResponse
                Timber.d(
                    " api res = ${tr.response.bot_response.finances.split_response}"
                )
            }

            override fun onFailure(call: Call<GoroskopResponse>, t: Throwable) {
                Timber.e(t.message ?: "")
            }
        })
    }
}