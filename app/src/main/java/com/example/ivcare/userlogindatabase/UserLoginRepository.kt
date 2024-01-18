package com.example.ivcare.userlogindatabase

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserLoginRepository {

    companion object {

        var loginDatabase: UserLoginDatabase? = null

        var loginTableModel: LiveData<UserLoginEntity>? = null

        fun initializeDB(context: Context) : UserLoginDatabase {
            return UserLoginDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, username: String, password: String) {
            loginDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = UserLoginEntity(username, password)
                loginDatabase!!.userDao().InsertData(loginDetails)
            }
        }

        fun getLoginDetails(context: Context, username: String, password: String) : LiveData<UserLoginEntity>? {
            loginDatabase = initializeDB(context)
            loginTableModel = loginDatabase!!.userDao().getLoginDetails(username,password)
            return loginTableModel
        }

        // New function to check if the username exists
        fun isUsernameExists(context: Context, username: String): Boolean {
            loginDatabase = initializeDB(context)
            val user = loginDatabase!!.userDao().getUserByUsername(username)
            return user != null
        }
    }
}
