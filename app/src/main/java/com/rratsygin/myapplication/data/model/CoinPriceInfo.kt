package com.rratsygin.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rratsygin.myapplication.data.network.ApiFactory.BASE_IMAGE_URL
import com.rratsygin.myapplication.utils.convertTimestampToTime


@Entity(tableName = "full_price_list")
data class CoinPriceInfo (
    @SerializedName("TYPE")
    @Expose
    val type: String? = null,

    @SerializedName("MARKET")
    @Expose
    val market: String? = null,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String? = null,

    @SerializedName("FLAGS")
    @Expose
    val flags: String? = null,

    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String? = null,

    @SerializedName("MEDIAN")
    @Expose
    val median: String? = null,

    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    val toptiervolume24hour: String? = null,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    @Expose
    val toptiervolume24hourto: String? = null,

    @SerializedName("LASTTRADEID")
    @Expose
    val lasttradeid: String? = null,

    @SerializedName("PRICE")
    @Expose
    val price: String? = null,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long? = null,

    @SerializedName("LASTVOLUME")
    @Expose
    val lastvolume: String? = null,

    @SerializedName("LASTVOLUMETO")
    @Expose
    val lastvolumeto: String? = null,

    @SerializedName("VOLUMEHOUR")
    @Expose
    val volumehour: String? = null,

    @SerializedName("VOLUMEHOURTO")
    @Expose
    val volumehourto: String? = null,

    @SerializedName("OPENHOUR")
    @Expose
    val openhour: String? = null,

    @SerializedName("HIGHHOUR")
    @Expose
    val highhour: String? = null,

    @SerializedName("LOWHOUR")
    @Expose
    val lowhour: String? = null,

    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeday: String? = null,

    @SerializedName("VOLUMEDAYTO")
    @Expose
    val volumedayto: String? = null,

    @SerializedName("OPENDAY")
    @Expose
    val openday: String? = null,

    @SerializedName("HIGHDAY")
    @Expose
    val highday: String? = null,

    @SerializedName("LOWDAY")
    @Expose
    val lowday: String? = null,

    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24hour: String? = null,

    @SerializedName("VOLUME24HOURTO")
    @Expose
    val volume24hourto: String? = null,

    @SerializedName("OPEN24HOUR")
    @Expose
    val open24hour: String? = null,

    @SerializedName("HIGH24HOUR")
    @Expose
    val high24hour: String? = null,

    @SerializedName("LOW24HOUR")
    @Expose
    val low24hour: String? = null,

    @SerializedName("CHANGE24HOUR")
    @Expose
    val change24hour: String? = null,

    @SerializedName("CHANGEPCT24HOUR")
    @Expose
    val changepct24hour: String? = null,

    @SerializedName("CHANGEDAY")
    @Expose
    val changeday: String? = null,

    @SerializedName("CHANGEPCTDAY")
    @Expose
    val changepctday: String? = null,

    @SerializedName("CHANGEHOUR")
    @Expose
    val changehour: String? = null,

    @SerializedName("CHANGEPCTHOUR")
    @Expose
    val changepcthour: String? = null,

    @SerializedName("CONVERSIONTYPE")
    @Expose
    val conversiontype: String? = null,

    @SerializedName("CONVERSIONSYMBOL")
    @Expose
    val conversionsymbol: String? = null,

    @SerializedName("CONVERSIONLASTUPDATE")
    @Expose
    val conversionlastupdate: Int? = null,

    @SerializedName("SUPPLY")
    @Expose
    val supply: String? = null,

    @SerializedName("MKTCAP")
    @Expose
    val mktcap: String? = null,

    @SerializedName("MKTCAPPENALTY")
    @Expose
    val mktcappenalty: String? = null,

    @SerializedName("CIRCULATINGSUPPLY")
    @Expose
    val circulatingsupply: String? = null,

    @SerializedName("CIRCULATINGSUPPLYMKTCAP")
    @Expose
    val circulatingsupplymktcap: String? = null,

    @SerializedName("TOTALVOLUME24H")
    @Expose
    val totalvolume24h: String? = null,

    @SerializedName("TOTALVOLUME24HTO")
    @Expose
    val totalvolume24hto: String? = null,

    @SerializedName("TOTALTOPTIERVOLUME24H")
    @Expose
    val totaltoptiervolume24h: String? = null,

    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    @Expose
    val totaltoptiervolume24hto: String? = null,

    @SerializedName("IMAGEURL")
    @Expose
    val imageurl: String? = null
) {

    fun getFormattedTime() : String {
        return convertTimestampToTime(lastUpdate)
    }


    fun getFullImageUrl() : String {
        return BASE_IMAGE_URL + imageurl
    }

}