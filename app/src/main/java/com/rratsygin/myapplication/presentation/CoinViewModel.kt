package com.rratsygin.myapplication.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.rratsygin.myapplication.data.network.ApiFactory
import com.rratsygin.myapplication.data.database.AppDatabase
import com.rratsygin.myapplication.data.network.model.CoinInfoDto
import com.rratsygin.myapplication.data.network.model.CoinInfoJsonContainerDto
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()

    private val db = AppDatabase.getInstance(application)

    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun getDetailInfo(fSym: String): LiveData<CoinInfoDto> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }


    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinInfo()
            .map {
                it.names?.map { it.coinNameDto?.name }?.joinToString(",")
            }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                db.coinPriceInfoDao().insertPriceList(it)
                Log.d("TEST_OF_LOADING_DATA", it.toString())
            }, {
                it.message?.let { it1 -> Log.d("TEST_OF_LOADING_DATA", it1) }
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(
        coinInfoJsonContainerDto: CoinInfoJsonContainerDto
    ): List<CoinInfoDto> {
        val result = ArrayList<CoinInfoDto>()
        val jsonObject = coinInfoJsonContainerDto.json
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}