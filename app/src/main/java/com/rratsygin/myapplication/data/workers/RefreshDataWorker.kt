package com.rratsygin.myapplication.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.rratsygin.myapplication.data.database.AppDatabase
import com.rratsygin.myapplication.data.mapper.CoinMapper
import com.rratsygin.myapplication.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(context : Context,
                        workerParameters: WorkerParameters
)  : CoroutineWorker(context, workerParameters) {

    private val mapper = CoinMapper()
    private val coinInfoDao = AppDatabase.getInstance(context).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService
    override suspend fun doWork(): Result {
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
    companion object {
        const val WORKER_NAME = "RefreshDataWorker"

        fun makeRequest() : OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }


    }
