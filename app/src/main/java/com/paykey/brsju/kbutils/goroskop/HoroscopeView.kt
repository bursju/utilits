package com.paykey.brsju.kbutils.goroskop


import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.paykey.brsju.kbutils.R


class HoroscopeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    lateinit var tvText: TextView

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val view = inflate(context, R.layout.horoscope_layout, this)
        tvText = view.findViewById(R.id.tvText)
    }
}