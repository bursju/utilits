package com.paykey.brsju.kbutils.goroskop


class GoroskopResponse(val response: GResponse)

class GResponse(val bot_response: BotResponse)

class BotResponse(val finances: Finances)

class Finances(val split_response: String)