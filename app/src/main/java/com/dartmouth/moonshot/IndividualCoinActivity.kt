package com.dartmouth.moonshot

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class IndividualCoinActivity: AppCompatActivity() {
    private lateinit var closeButton: Button
    private lateinit var moreInfoButton: Button

    private var address: String? = null
    private var blockchainType: String? = null
    private var currentPrice: String? = null
    private var dailyVolume: String? = null
    private var holders: String? = null
    private var name: String? = null
    private var symbol: String? = null

    companion object coinSPKeys {
        val BUNDLE_KEY = "bundle"
        val ADDRESS_KEY = "address"
        val BLOCKCHAIN_TYPE_KEY = "blockchainType"
        val CURRENT_PRICE_KEY = "currentPrice"
        val DAILY_VOLUME_KEY = "dailyVolume"
        val HOLDERS_KEY = "holders"
        val NAME_KEY = "name"
        val SYMBOL_KEY = "symbol"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_coin)

        closeButton = findViewById(R.id.button_close)
        closeButton.setOnClickListener(){
            finish()
        }

        moreInfoButton = findViewById(R.id.button_more_info)
        moreInfoButton.setOnClickListener(){
            showMoreInfo()
        }
    }

    override fun onResume() {
        super.onResume()
        // Get coin info from intent
        val bundle = intent.getBundleExtra(BUNDLE_KEY)
        address = bundle!!.getString(ADDRESS_KEY)
        blockchainType = bundle!!.getString(BLOCKCHAIN_TYPE_KEY)
        currentPrice = bundle!!.getString(CURRENT_PRICE_KEY)
        dailyVolume = bundle!!.getString(DAILY_VOLUME_KEY)
        holders = bundle!!.getString(HOLDERS_KEY)
        name = bundle!!.getString(NAME_KEY)
        symbol = bundle!!.getString(SYMBOL_KEY)
    }

    fun showMoreInfo(){
        val intent = Intent(this, CoinWebpageActivity::class.java)
        // Hardcode
        //intent.putExtra(CoinWebpageActivity.COIN_ADDRESS_KEY, address)
        intent.putExtra(CoinWebpageActivity.COIN_ADDRESS_KEY, "0xb45f65561bdbef60e6f716a755394efee977c4ac")
        this.startActivity(intent)
    }
}