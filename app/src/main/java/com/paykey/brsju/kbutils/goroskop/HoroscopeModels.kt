package com.paykey.brsju.kbutils.goroskop


class VedicastroapiResponse(val response: HoroscopeResponse)

class HoroscopeResponse(val bot_response: BotResponse)

class BotResponse(val finances: Finances)

class Finances(val split_response: String)