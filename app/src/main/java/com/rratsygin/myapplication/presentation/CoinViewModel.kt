package com.rratsygin.myapplication.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rratsygin.myapplication.data.repository.CoinInfoRepositoryImpl
import com.rratsygin.myapplication.domain.GetCoinInfoListUseCase
import com.rratsygin.myapplication.domain.GetCoinInfoUseCase
import com.rratsygin.myapplication.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinInfoRepositoryImpl(application)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()



    fun getDetailInfo(fSym: String) =   getCoinInfoUseCase(fSym)


    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

    }


