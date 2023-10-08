package com.rratsygin.myapplication.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.rratsygin.myapplication.data.database.AppDatabase
import com.rratsygin.myapplication.data.mapper.CoinMapper
import com.rratsygin.myapplication.data.network.ApiFactory
import com.rratsygin.myapplication.data.workers.RefreshDataWorker
import com.rratsygin.myapplication.domain.CoinInfo
import com.rratsygin.myapplication.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinInfoRepositoryImpl(private val application: Application) : CoinRepository {

    private val mapper = CoinMapper()
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()


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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.WORKER_NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()

        )
    }

}