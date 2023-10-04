package com.rratsygin.myapplication.domain

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lastMarket: String?,
    val lastUpdate: String?,
    val highday: String?,
    val lowday: String?,
    val imageurl: String
)