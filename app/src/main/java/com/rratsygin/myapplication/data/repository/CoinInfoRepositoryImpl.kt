package com.rratsygin.myapplication.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.rratsygin.myapplication.data.database.AppDatabase
import com.rratsygin.myapplication.data.mapper.CoinMapper
import com.rratsygin.myapplication.data.network.ApiFactory
import com.rratsygin.myapplication.domain.CoinInfo
import com.rratsygin.myapplication.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinInfoRepositoryImpl(private val application: Application) : CoinRepository{

    private val mapper = CoinMapper()
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList().map {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }



    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                Log.d("Loading", "I`m here")
                coinInfoDao.insertPriceList(dbModelList)
                Log.d("Loading", "I`m here 2222")
            } catch (e: Exception) {

            }
            delay(10000)
        }

    }
}