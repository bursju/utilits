package com.paykey.brsju.kbutils

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView


class TimeConverterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    lateinit var tvDate: TextView
    lateinit var tvTime: TextView
    lateinit var tvTimeZone: TextView

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {

        val view = inflate(context, R.layout.time_converter_layout, this)

        tvDate = view.findViewById(R.id.tvData)
        tvTime = view.findViewById(R.id.tvTime)
        tvTimeZone = view.findViewById(R.id.tvTimeZone)
    }
}