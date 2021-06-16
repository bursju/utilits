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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goroskop)
    }
}