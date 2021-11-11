package com.dartmouth.moonshot

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson




class ProfileRepository {

    private var liveData: MutableLiveData<User>? = null
    private lateinit var databaseReference: DatabaseReference

    private var mFirebaseAuth = FirebaseAuth.getInstance()
    private var storageReference = FirebaseStorage.getInstance().getReference("Users/"+mFirebaseAuth?.currentUser?.uid)

    object StaticFunction {
        private var instance: ProfileRepository? = null
        fun getInstance(): ProfileRepository {
            if (instance == null)
                instance = ProfileRepository()

            return instance!!
        }
    }

    fun getUser(): LiveData<User> {

        try {
            if (liveData == null)
                liveData = MutableLiveData()
            databaseReference =
                Firebase.database.getReference("Users").child(mFirebaseAuth!!.currentUser!!.uid)
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val userModel = snapshot.getValue(User::class.java)
                        liveData!!.postValue(userModel)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }catch (exception: Exception){
            println("signout")
        }
        return liveData!!
    }

    fun updateName(userName: String?) {


        val databaseReference: DatabaseReference =
            Firebase.database.getReference("Users").child(mFirebaseAuth!!.currentUser!!.uid)
        val map = mapOf<String, Any>("name" to userName!!)
        databaseReference.updateChildren(map)

    }

    fun updateBio(bioText: String?) {


        val databaseReference: DatabaseReference =
            Firebase.database.getReference("Users").child(mFirebaseAuth!!.currentUser!!.uid)
        val map = mapOf<String, Any>("bio" to bioText!!)
        databaseReference.updateChildren(map)

    }

    /*fun updateStatus(status: String) {

        val databaseReference =
            Firebase.database.getReference("Users").child(mFirebaseAuth!!.currentUser!!.uid)

        val map = mapOf<String, Any>("status" to status)
        databaseReference.updateChildren(map)

    }*/

    fun updateImage(imagePath: Uri, changeImage: Int) {
        if (changeImage == 1){
            val databaseReference =
                Firebase.database.getReference("Users").child(mFirebaseAuth!!.currentUser!!.uid)

            val map = mapOf<String, Any>("image" to imagePath.toString())
            databaseReference.updateChildren(map)

            storageReference.putFile(imagePath).addOnSuccessListener {
                //Toast.makeText(this.activity, "profile image uploaded", Toast.LENGTH_SHORT).show()
                println("IMAGE UPLOADED")
            }.addOnFailureListener{
                //Toast.makeText(this.activity, "failed profile image upload", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun fromString(value: String?): ArrayList<String>? {
        val listType = object : TypeToken<ArrayList<String>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    fun fromArrayList(list: ArrayList<String>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    fun updateSavedCoins(coinList: ArrayList<String>?) {
        var listAsString = fromArrayList(coinList)

        val databaseReference: DatabaseReference =
            Firebase.database.getReference("Users").child(mFirebaseAuth!!.currentUser!!.uid)
        val map = mapOf<String, Any>("savedCoins" to listAsString!!)
        databaseReference.updateChildren(map)

    }


}