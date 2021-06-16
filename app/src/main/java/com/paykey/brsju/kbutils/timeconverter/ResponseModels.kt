package com.paykey.brsju.kbutils.timeconverter


class TimeZonesResponse(
    val zones: List<LocalTimeResponse>
)

class LocalTimeResponse(
    val timestamp: Long,
    val countryName: String,
    val zoneName: String,
    val gmtOffset: Long
)

class ConvertTimeResponse(
    val toTimestamp: Long,
    val offset: String
)