package com.paykey.brsju.kbutils.horoscope


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paykey.brsju.kbutils.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class HoroscopeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    lateinit var horoscopeView: LinearLayout
    lateinit var tvHoroscopePrediction: TextView
    private lateinit var photoAdapter: PhotoAdapter
    private var dataList = mutableListOf<ZodiacModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var bBack: ImageView
    private lateinit var tvOffer: TextView
    private lateinit var offerView: LinearLayout
    private lateinit var ivOfferBack: ImageView

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val view = inflate(context, R.layout.horoscope_layout, this)
        horoscopeView = view.findViewById(R.id.horoscopeView)
        tvHoroscopePrediction = view.findViewById(R.id.tvHoroscopePrediction)
        recyclerView = view.findViewById(R.id.rvZodiacsGrid)
        bBack = view.findViewById(R.id.ivBack)
        tvOffer = view.findViewById(R.id.tvOffer)
        offerView = view.findViewById(R.id.offerView)
        ivOfferBack = view.findViewById(R.id.ivOfferBack)
        recyclerView.layoutManager = GridLayoutManager(context, 3)

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

                    horoscopeView.visibility = View.VISIBLE
                    tvHoroscopePrediction.text = tr.response.bot_response.finances.split_response
                    bBack.setOnClickListener {
                        horoscopeView.visibility = View.GONE
                        bBack.setOnClickListener(null)
                    }
                    if (zodiacId == 1) {
                        tvOffer.visibility = View.VISIBLE
                        tvOffer.setOnClickListener {
                            offerView.visibility = View.VISIBLE
                            ivOfferBack.setOnClickListener {
                                offerView.visibility = View.GONE
                                ivOfferBack.setOnClickListener(null)
                            }
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