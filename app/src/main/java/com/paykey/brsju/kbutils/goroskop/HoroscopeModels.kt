package com.paykey.brsju.kbutils.goroskop


class VedicastroapiResponse<T>(val response: T)

class HoroscopeResponse(val bot_response: BotResponse)
class BotResponse(val finances: Finances)
class Finances(val split_response: String, val score: String)