package com.example.ivcare.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ivcare.userlogindatabase.UserLoginEntity
import com.example.ivcare.userlogindatabase.UserLoginRepository

class ViewModelUser : ViewModel() {

    var liveDataLogin: LiveData<UserLoginEntity>? = null

    fun insertData(context: Context, username: String, password: String) {
        UserLoginRepository.insertData(context, username, password)
    }

    fun getLoginDetails(context: Context, username: String, password: String): LiveData<UserLoginEntity>? {
        liveDataLogin = UserLoginRepository.getLoginDetails(context, username, password)
        return liveDataLogin
    }


}
