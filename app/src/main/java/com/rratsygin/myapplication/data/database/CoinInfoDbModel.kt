package com.rratsygin.myapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rratsygin.myapplication.data.network.ApiFactory.BASE_IMAGE_URL
import com.rratsygin.myapplication.utils.convertTimestampToTime


@Entity(tableName = "full_price_list")
data class CoinInfoDbModel (
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String? = null,
    val price: String? = null,
    val lastMarket: String? = null,
    val lastUpdate: Long? = null,
    val highday: String? = null,
    val lowday: String? = null,
    val imageurl: String? = null
) 