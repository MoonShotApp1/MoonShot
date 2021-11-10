package com.dartmouth.moonshot

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {
    private var profileRepo = ProfileRepository.StaticFunction.getInstance()

    fun getUser(): LiveData<User> {
        return profileRepo.getUser()
    }

    /*fun updateStatus(status: String) {
        profileRepo.updateStatus(status)

    }*/

    fun updateName(userName: String?) {
        profileRepo.updateName(userName!!)
    }

    fun updateBio(bioText: String?) {
        profileRepo.updateBio(bioText!!)
    }

    fun updateImage(imagePath: Uri, changeImg: Int) {
        profileRepo.updateImage(imagePath, changeImg)
    }

    fun updateSavedCoins(coinList: ArrayList<String>) {
        profileRepo.updateSavedCoins(coinList)
    }
}