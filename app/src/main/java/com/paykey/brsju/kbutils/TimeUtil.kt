package com.paykey.brsju.kbutils

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

val simpleDateFormat = SimpleDateFormat("EEE, dd MMM ", Locale.US)
val simpleDateTimeFormat = SimpleDateFormat("hh:mma", Locale.US)

fun getDateTime(timestamp: Long, sdf: SimpleDateFormat): String? {
    return try {
        Timber.d(" hello timestamp = $timestamp ")
        val date = Date(timestamp * 1000)
        val formattedDate = sdf.format(date)
        formattedDate
    } catch (e: Exception) {
        return null
    }
}