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

    fun updateImage(imagePath: Uri) {
        profileRepo.updateImage(imagePath)
    }
}