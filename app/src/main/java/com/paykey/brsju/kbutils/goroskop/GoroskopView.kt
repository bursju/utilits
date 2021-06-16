package com.paykey.brsju.kbutils.goroskop


import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.paykey.brsju.kbutils.R


class GoroskopView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    lateinit var tvText: TextView

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val view = inflate(context, R.layout.goroskop_layout, this)
        tvText = view.findViewById(R.id.tvText)
    }
}