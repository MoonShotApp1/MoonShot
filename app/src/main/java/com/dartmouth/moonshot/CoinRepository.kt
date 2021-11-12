package com.dartmouth.moonshot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CoinRepository {

    private var liveData: MutableLiveData<ArrayList<Coin>>? = null
    private var liveDataSavedC: MutableLiveData<ArrayList<Coin>>? = null
    private lateinit var databaseReference: DatabaseReference

    private var mFirebaseAuth = FirebaseAuth.getInstance()

    object StaticFunction {
        private var instance: CoinRepository? = null
        fun getInstance(): CoinRepository {
            if (instance == null)
                instance = CoinRepository()

            return instance!!
        }
    }


    fun getAllCoins(): LiveData<ArrayList<Coin>> {
        var coinsList = ArrayList<Coin>()
        if (liveData == null){
            liveData = MutableLiveData()
        }
        databaseReference =
            Firebase.database.getReference("Coins")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for(snap in snapshot.children){
                        val coinModel = snap.getValue(Coin::class.java)
                        if (coinModel != null) {
                            coinsList.add(coinModel)
                        }
                    }
                    liveData!!.postValue(coinsList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return liveData!!
    }

    fun getSavedCoins(coinIDs: ArrayList<String>): LiveData<ArrayList<Coin>>{
        var savedCoinsList = ArrayList<Coin>()
        if (liveDataSavedC == null){
            liveDataSavedC = MutableLiveData()
        }
        databaseReference =
            Firebase.database.getReference("Coins")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for(snap in snapshot.children){
                        val coinModel = snap.getValue(Coin::class.java)
                        if (coinModel != null && coinIDs.contains(coinModel.id)) {
                            savedCoinsList.add(coinModel)
                        }
                    }
                    liveDataSavedC!!.postValue(savedCoinsList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return liveDataSavedC!!
    }


}