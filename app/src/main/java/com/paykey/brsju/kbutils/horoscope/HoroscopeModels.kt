package com.paykey.brsju.kbutils.horoscope


class VedicastroapiResponse<T>(val response: T)

class HoroscopeResponse(val bot_response: BotResponse)
class BotResponse(val finances: Finances)
class Finances(val split_response: String, val score: String)

data class ZodiacModel(
    var image: Int,
    val zodiacId: Int = 1,
    val zodiacName: String = ""
)