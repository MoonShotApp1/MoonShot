package com.dartmouth.moonshot

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class IndividualCoinActivity: AppCompatActivity() {
    private lateinit var closeButton: Button
    private lateinit var moreInfoButton: Button
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

    }

    fun showMoreInfo(){
        val intent = Intent(this, CoinWebpageActivity::class.java)
        // Hardcode
        intent.putExtra(CoinWebpageActivity.COIN_ADDRESS_KEY, "0xb45f65561bdbef60e6f716a755394efee977c4ac")
        this.startActivity(intent)
    }
}