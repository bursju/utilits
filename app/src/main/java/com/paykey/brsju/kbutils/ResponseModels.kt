package com.paykey.brsju.kbutils


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