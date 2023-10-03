package com.rratsygin.myapplication.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import org.json.JSONObject


class CoinPriceInfoRawData {
    @SerializedName("RAW")
    @Expose
     val coinPriceInfoJsonObject : JsonObject? = null
}