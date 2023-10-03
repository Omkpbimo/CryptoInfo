package com.rratsygin.myapplication.domain

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String? = null,
    val price: String? = null,
    val lastMarket: String? = null,
    val lastUpdate: Long? = null,
    val highday: String? = null,
    val lowday: String? = null,
    val imageurl: String? = null
)