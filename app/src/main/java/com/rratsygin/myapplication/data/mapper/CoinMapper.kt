package com.rratsygin.myapplication.data.mapper

import com.google.gson.Gson
import com.rratsygin.myapplication.data.database.CoinInfoDbModel
import com.rratsygin.myapplication.data.network.model.CoinInfoDto
import com.rratsygin.myapplication.data.network.model.CoinInfoJsonContainerDto
import com.rratsygin.myapplication.data.network.model.CoinNamesListDto
import com.rratsygin.myapplication.domain.CoinInfo
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel {
        return CoinInfoDbModel(
            fromSymbol = dto.fromSymbol,
            toSymbol = dto.toSymbol,
            price = dto.price,
            lastMarket = dto.lastMarket,
            lastUpdate = dto.lastUpdate,
            highday = dto.highday,
            lowday = dto.lowday,
            imageurl = BASE_IMAGE_URL + dto.imageurl
        )
    }

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json
        if (jsonObject == null) return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey), CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }

        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map {
            it.coinNameDto?.name
        }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) : CoinInfo {
        return CoinInfo(
            fromSymbol = dbModel.fromSymbol,
            toSymbol = dbModel.toSymbol,
            price = dbModel.price,
            lastMarket = dbModel.lastMarket,
            lastUpdate = convertTimestampToTime(dbModel.lastUpdate),
            highday = dbModel.highday,
            lowday = dbModel.lowday,
            imageurl = dbModel.imageurl
        )
    }

    private fun convertTimestampToTime(timestamp: Long?) : String {
        if (timestamp== null) return ""
        val stamp = Timestamp(timestamp*1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getDefault()
        return simpleDateFormat.format(date)

}

    companion object {

        private const val BASE_IMAGE_URL = "https://cryptocompare.com"

    }    }

