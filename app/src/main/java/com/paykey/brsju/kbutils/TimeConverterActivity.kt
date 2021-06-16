package com.paykey.brsju.kbutils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.*


class TimeConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val root = findViewById<TimeConverterView>(R.id.root)

        val timezonedbApi = TimezonedbApi.client().create(TimezonedbApi::class.java)
        val tz = TimeZone.getDefault()
        val call: Call<LocalTimeResponse> = timezonedbApi.getLocalTimeZone(zone = tz.id)
        call.enqueue(object : Callback<LocalTimeResponse> {

            override fun onResponse(call: Call<LocalTimeResponse>, responseLocal: Response<LocalTimeResponse>) {

                val tr: LocalTimeResponse = responseLocal.body() as LocalTimeResponse
                val date = getDateTime(tr.timestamp, simpleDateFormat)
                val time = getDateTime(tr.timestamp - tr.gmtOffset, simpleDateTimeFormat)

                root.apply {
                    tvDate.text = date
                    tvTime.text = time
                    tvTimeZone.text = tz.id
                }
            }

            override fun onFailure(call: Call<LocalTimeResponse>, t: Throwable) {
                Timber.e(t.message ?: "")
            }
        })
    }
}