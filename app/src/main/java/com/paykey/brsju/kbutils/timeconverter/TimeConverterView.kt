package com.paykey.brsju.kbutils.timeconverter


import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import com.paykey.brsju.kbutils.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class TimeConverterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    lateinit var tvDate: TextView
    lateinit var tvTime: TextView
    lateinit var tvTimeZone: TextView
    lateinit var bAddTimeZone: ImageView
    lateinit var lvTimeZoneList: ListView
    private var zonesList: List<LocalTimeResponse>? = null

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val view = inflate(context, R.layout.time_converter_layout, this)

        tvDate = view.findViewById(R.id.tvData)
        tvTime = view.findViewById(R.id.tvTime)
        tvTimeZone = view.findViewById(R.id.tvTimeZone)
        bAddTimeZone = view.findViewById(R.id.bAddTimeZone)
        lvTimeZoneList = view.findViewById(R.id.lvTimeZoneList)

        bAddTimeZone.setOnClickListener {
            showTimeZoneList()
        }

        lvTimeZoneList.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            addConvertedTime(zonesList!![position].zoneName, zonesList!![position].countryName)
            lvTimeZoneList.visibility = View.GONE
        }
    }

    private fun addConvertedTime(timeZoneName: String, countryName: String) {
        val timezonedbApi = TimezonedbApi.client()
            .create(TimezonedbApi::class.java)

        val call: Call<ConvertTimeResponse> = timezonedbApi.convertTime(
            from = TimeZone.getDefault().id,
            to = timeZoneName
        )

        call.enqueue(object : Callback<ConvertTimeResponse> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<ConvertTimeResponse>, response: Response<ConvertTimeResponse>) {
                val result: ConvertTimeResponse = response.body() as ConvertTimeResponse

                val li = LayoutInflater.from(context)
                val child: View = li.inflate(R.layout.converted_time_item, null)
                val item = findViewById<View>(R.id.convertedTimeList) as LinearLayout

                val timeZone = child.findViewById<TextView>(R.id.tvTimeZone)
                val tvTime = child.findViewById<TextView>(R.id.tvTime)
                val tvOffset = child.findViewById<TextView>(R.id.tvOffset)

                tvOffset.text = "${TimeUnit.SECONDS.toHours(result.offset.toLong())} hours behind"
                timeZone.text = "$countryName $timeZoneName"
                tvTime.text = getDateTime(
                    result.toTimestamp,
                    simpleDateTimeFormat
                )

                item.addView(child)
            }

            override fun onFailure(call: Call<ConvertTimeResponse>, t: Throwable) {
                Timber.e(t.message ?: "")
            }
        })

    }

    private fun showTimeZoneList() {
        if (zonesList == null) {
            val timezonedbApi = TimezonedbApi.client()
                .create(TimezonedbApi::class.java)
            val call: Call<TimeZonesResponse> = timezonedbApi.getAvailableTimeZone()
            call.enqueue(object : Callback<TimeZonesResponse> {

                override fun onResponse(call: Call<TimeZonesResponse>, response: Response<TimeZonesResponse>) {
                    val timeZonesResponse: TimeZonesResponse = response.body() as TimeZonesResponse
                    zonesList = timeZonesResponse.zones
                    displayList(zonesList!!)
                }

                override fun onFailure(call: Call<TimeZonesResponse>, t: Throwable) {
                    Timber.e(t.message ?: "")
                }
            })
        } else {
            displayList(zonesList!!)
        }

    }

    private fun displayList(localTimeResponse: List<LocalTimeResponse>) {
        val result = ArrayList<String>()

        localTimeResponse.forEach {
            val timeZoneInfo = "${it.countryName} ${it.zoneName}"
            result.add(timeZoneInfo)
        }

        val adapter = TimeZonesArrayAdapter(
            context,
            android.R.layout.simple_list_item_1,
            result
        )

        lvTimeZoneList.adapter = adapter
        lvTimeZoneList.visibility = View.VISIBLE
    }

    private class TimeZonesArrayAdapter(context: Context?, textViewResourceId: Int, objects: List<String>) :
        ArrayAdapter<String?>(context!!, textViewResourceId, objects) {
        var mIdMap = HashMap<String, Int>()

        override fun getItemId(position: Int): Long {
            val item = getItem(position)
            return mIdMap[item]!!.toLong()
        }

        override fun hasStableIds(): Boolean {
            return true
        }

        init {
            for (i in objects.indices) {
                mIdMap[objects[i]] = i
            }
        }
    }
}