package com.rratsygin.myapplication.domain

class LoadDataUseCase (private val repository: CoinRepository){
    operator fun invoke() = repository.loadData()

}