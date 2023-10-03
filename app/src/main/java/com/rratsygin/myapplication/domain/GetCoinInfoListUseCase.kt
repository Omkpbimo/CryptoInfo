package com.rratsygin.myapplication.domain

class GetCoinInfoListUseCase (private val repository: CoinRepository){
    operator fun invoke() = repository.getCoinInfoList()

}