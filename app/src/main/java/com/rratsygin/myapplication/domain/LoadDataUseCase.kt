package com.rratsygin.myapplication.domain

class LoadDataUseCase (private val repository: CoinRepository){
    operator suspend fun invoke() = repository.loadData()

}