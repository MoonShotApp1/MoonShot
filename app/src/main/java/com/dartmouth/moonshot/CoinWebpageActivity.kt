package com.dartmouth.moonshot

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.app.ProgressDialog
import android.webkit.WebSettings

class CoinWebpageActivity: AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var closeButton: Button
    private var coinUrl: String? = null
    private var progress: ProgressDialog? = null

    companion object{
        val COIN_ADDRESS_KEY = "CoinAddress"
        val BLOCKCHAIN_TYPE_KEY = "blockchainType"
        val COIN_NAME_KEY = "CoinName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_webpage)

        closeButton = findViewById(R.id.button_close)
        closeButton.setOnClickListener(){
            finish()
        }

        webView = findViewById(R.id.webview)

        progress = ProgressDialog.show(this, "Loading coin on Dextools","Please wait...", true)

        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        val settings: WebSettings = webView.getSettings()
        settings.domStorageEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                progress!!.show()
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                Log.d("debug", "here")
                Log.d("debug", "url = $url")
                progress!!.dismiss()
            }
        }


        // Get url
        val coinName = intent.getStringExtra(COIN_NAME_KEY)
        //coinUrl = "https://www.coingecko.com/en/coins/" + coinName

        val coinAddress = intent.getStringExtra(COIN_ADDRESS_KEY)
        val blockChainType = intent.getStringExtra(BLOCKCHAIN_TYPE_KEY)
        if(blockChainType == "binance-smart-chain"){
            coinUrl = "https://www.dextools.io/app/bsc/pair-explorer/" + coinAddress
        } else if (blockChainType == "ethereum") {
            coinUrl = "https://www.dextools.io/app/ether/pair-explorer/" + coinAddress
        }
        Log.d("debug", "coinUrl = $coinUrl")
        webView.loadUrl(coinUrl!!)
    }
}