package com.dartmouth.moonshot

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CoinViewModel: ViewModel() {
    private var coinRepo = CoinRepository.StaticFunction.getInstance()

    fun getAllCoins(): LiveData<ArrayList<Coin>> {
        return coinRepo.getAllCoins()
    }

    fun getSavedCoins(coinIDList: ArrayList<String>): LiveData<ArrayList<Coin>> {
        return coinRepo.getSavedCoins(coinIDList)
    }


}