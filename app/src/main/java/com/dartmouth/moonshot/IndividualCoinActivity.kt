package com.dartmouth.moonshot

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso

class IndividualCoinActivity: AppCompatActivity() {
    private lateinit var closeButton: Button
    private lateinit var moreInfoButton: Button
    private lateinit var saveButton: Button

    private lateinit var profileViewModel: ProfileViewModel

    private var mFirebaseAuth: FirebaseAuth? = null

    private var address: String? = null
    private var blockchainType: String? = null
    private var currentPrice: String? = null
    private var dailyVolume: String? = null
    private var holders: String? = null
    private var name: String? = null
    private var symbol: String? = null
    private var imageLarge: String? = null
    private var coinID: String? = null

    companion object coinSPKeys {
        val BUNDLE_KEY = "bundle"
        val ADDRESS_KEY = "address"
        val BLOCKCHAIN_TYPE_KEY = "blockchainType"
        val CURRENT_PRICE_KEY = "currentPrice"
        val DAILY_VOLUME_KEY = "dailyVolume"
        val HOLDERS_KEY = "holders"
        val NAME_KEY = "name"
        val SYMBOL_KEY = "symbol"
        val IMAGE_LARGE_KEY = "image-large"
        val ID_KEY = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_coin)

        closeButton = findViewById(R.id.button_close)
        closeButton.setOnClickListener(){
            finish()
        }

        address = intent.getStringExtra(ADDRESS_KEY)

        mFirebaseAuth = FirebaseAuth.getInstance()
        //profileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        profileViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                .create(ProfileViewModel::class.java)

        var savedCoinsList = fromString(profileViewModel.getUser().value?.savedCoins)
        //Toast.makeText(this, savedCoinsList.toString(), Toast.LENGTH_LONG).show()

        saveButton = findViewById(R.id.button_save)
        saveButton.setOnClickListener(){
            if (savedCoinsList != null) {
                address?.let { it1 -> savedCoinsList.add(it1) }
                profileViewModel.updateSavedCoins(savedCoinsList)
            }
            finish()
        }

        moreInfoButton = findViewById(R.id.web)
        moreInfoButton.setOnClickListener(){
            showMoreInfo()
        }

        imageLarge = intent.getStringExtra(IMAGE_LARGE_KEY)
        coinID = intent.getStringExtra(ID_KEY)


        val nameTextView: TextView = findViewById(R.id.coinName)
        val symbolTextView: TextView = findViewById(R.id.coinSymbol)
        val bcTypeTextView: TextView = findViewById(R.id.blockChainTypeAB)
        val currPriceTextView: TextView = findViewById(R.id.price)
        val imageLargeImageView: ImageView = findViewById(R.id.coinPic)

        nameTextView.text = intent.getStringExtra(NAME_KEY)
        symbolTextView.text = intent.getStringExtra(SYMBOL_KEY)
        bcTypeTextView.text = intent.getStringExtra(BLOCKCHAIN_TYPE_KEY)
        currPriceTextView.text = intent.getStringExtra(CURRENT_PRICE_KEY)
        Picasso.get().load(imageLarge).into(imageLargeImageView)
    }


    override fun onResume() {
        super.onResume()
        // Get coin info from intent
        //val bundle = intent.getBundleExtra(BUNDLE_KEY)
        address = intent.getStringExtra(ADDRESS_KEY)
        blockchainType = intent.getStringExtra(BLOCKCHAIN_TYPE_KEY)
        currentPrice = intent.getStringExtra(CURRENT_PRICE_KEY)
        //dailyVolume = intent.getStringExtra(DAILY_VOLUME_KEY)
        //holders = intent.getStringExtra(HOLDERS_KEY)
        name = intent.getStringExtra(NAME_KEY)
        symbol = intent.getStringExtra(SYMBOL_KEY)
    }

    fun showMoreInfo(){
        val intent = Intent(this, CoinWebpageActivity::class.java)
        // Hardcode
        intent.putExtra(CoinWebpageActivity.COIN_ADDRESS_KEY, address)
        intent.putExtra(CoinWebpageActivity.COIN_ID_KEY, coinID)
        //intent.putExtra(CoinWebpageActivity.BLOCKCHAIN_TYPE_KEY, blockchainType)
        //intent.putExtra(CoinWebpageActivity.COIN_ADDRESS_KEY, "0xb45f65561bdbef60e6f716a755394efee977c4ac")
        this.startActivity(intent)
    }

    fun fromString(value: String?): ArrayList<String>? {
        val listType = object : TypeToken<ArrayList<String>?>() {}.type
        return Gson().fromJson(value, listType)
    }
}