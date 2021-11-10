package com.dartmouth.moonshot

data class Coin(
    var name: String? = null,
    var symbol: String? = null,
    var holders: String? = null,
    var address: String? = null,
    var dailVolume: String? = null,
    var currentPrice: String? = null,
    var priceChange24: String? = null,
    var totalTransactions: String? = null,
    var price24Low: String? = null,
    var price24High: String? = null,
    var blockchainType: String? = null
)
