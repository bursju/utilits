package com.paykey.brsju.kbutils.horoscope

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paykey.brsju.kbutils.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class HoroscopeActivity : AppCompatActivity() {

    private lateinit var photoAdapter: PhotoAdapter
    private var dataList = mutableListOf<ZodiacModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var bBack: ImageView
    private lateinit var tvOffer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goroskop)

        val root = findViewById<HoroscopeView>(R.id.root)

        recyclerView = findViewById(R.id.rvZodiacsGrid)
        bBack = findViewById(R.id.ivBack)
        tvOffer = findViewById(R.id.tvOffer)

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        photoAdapter = PhotoAdapter {

            val goroskopApi = HoroscopeApi.client().create(HoroscopeApi::class.java)
            val call = goroskopApi.getDailyHoroscope(zodiac = zodiacId)
            call.enqueue(object : Callback<VedicastroapiResponse<HoroscopeResponse>> {

                override fun onResponse(
                    call: Call<VedicastroapiResponse<HoroscopeResponse>>,
                    responseLocal: Response<VedicastroapiResponse<HoroscopeResponse>>
                ) {

                    val tr = responseLocal.body() as VedicastroapiResponse
                    Timber.d(" \n api res = ${tr.response.bot_response.finances.split_response}  \n score = ${tr.response.bot_response.finances.score} ")

                    root.horoscopeView.visibility = View.VISIBLE
                    root.tvHoroscopePrediction.text = tr.response.bot_response.finances.split_response
                    bBack.setOnClickListener {
                        root.horoscopeView.visibility = View.GONE
                        bBack.setOnClickListener(null)
                    }
                    if (zodiacId == 1) {
                        tvOffer.visibility =View.VISIBLE
                        tvOffer.setOnClickListener {
                            //dfvefv
                        }
                    }
                }

                override fun onFailure(call: Call<VedicastroapiResponse<HoroscopeResponse>>, t: Throwable) {
                    Timber.e(t.message ?: "")
                }
            })

        }
        recyclerView.adapter = photoAdapter

        //add data
        dataList.add(ZodiacModel(R.drawable.ic_virgo))
        dataList.add(ZodiacModel(R.drawable.zodiac))
        dataList.add(ZodiacModel(R.drawable.zodiac))

        dataList.add(ZodiacModel(R.drawable.zodiac))
        dataList.add(ZodiacModel(R.drawable.zodiac))
        dataList.add(ZodiacModel(R.drawable.zodiac))

        dataList.add(ZodiacModel(R.drawable.zodiac))
        dataList.add(ZodiacModel(R.drawable.zodiac))
        dataList.add(ZodiacModel(R.drawable.zodiac))

        dataList.add(ZodiacModel(R.drawable.zodiac))
        dataList.add(ZodiacModel(R.drawable.zodiac))
        dataList.add(ZodiacModel(R.drawable.zodiac))

        photoAdapter.setDataList(dataList)
    }
}