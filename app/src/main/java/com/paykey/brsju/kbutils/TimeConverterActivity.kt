package com.paykey.brsju.kbutils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


class TimeConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val root = findViewById<TimeConverterView>(R.id.root)

        val timezonedbApi = TimezonedbApi.client().create(TimezonedbApi::class.java)
        val tz = TimeZone.getDefault()
        val call: Call<TimeResult> = timezonedbApi.getLocalTimeZone(zone = tz.id)
        call.enqueue(object : Callback<TimeResult> {

            override fun onResponse(call: Call<TimeResult>, response: Response<TimeResult>) {
                val tr: TimeResult = response.body() as TimeResult
                val date = getDateTime(tr.timestamp.toLong(), simpleDateFormat)
                val time = getDateTime(tr.timestamp.toLong(), simpleDateTimeFormat)

                root.apply {
                    tvDate.text = date
                    tvTime.text = time
                    tvTimeZone.text = tz.id
                }
            }

            override fun onFailure(call: Call<TimeResult>, t: Throwable) {
                Timber.e(t.message ?: "")
            }
        })
    }
}

private val simpleDateFormat = SimpleDateFormat("EEE, dd MMM ", Locale.ENGLISH)
private val simpleDateTimeFormat = SimpleDateFormat("hh:mm", Locale.ENGLISH)

private fun getDateTime(timestamp: Long, sdf: SimpleDateFormat): String? {
    return try {
        sdf.format(timestamp * 1000L)
    } catch (e: Exception) {
        return null
    }
}