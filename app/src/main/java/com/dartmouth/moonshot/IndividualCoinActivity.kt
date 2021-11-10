package com.dartmouth.moonshot

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IndividualCoinActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_coin)

        val nameTextView: TextView = findViewById(R.id.coinName)
        val symbolTextView: TextView = findViewById(R.id.coinSymbol)
        val bcTypeTextView: TextView = findViewById(R.id.blockChainTypeAB)
        val currPriceTextView: TextView = findViewById(R.id.price)

        nameTextView.text = intent.getStringExtra("name")
        symbolTextView.text = intent.getStringExtra("symbol")
        bcTypeTextView.text = intent.getStringExtra("BCType")
        currPriceTextView.text = intent.getStringExtra("currPrice")

    }

    fun onClickClose(view: View){
        finish()
    }
}