package com.rratsygin.myapplication.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class CoinInfoJsonContainerDto {
    @SerializedName("RAW")
    @Expose
     val json : JsonObject? = null
}