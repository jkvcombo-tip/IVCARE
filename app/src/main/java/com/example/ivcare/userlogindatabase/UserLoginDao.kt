package com.example.ivcare.userlogindatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserLoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(loginTableModel: UserLoginEntity)

    @Query("SELECT * FROM user WHERE Username = :username AND Password = :password")
    fun getLoginDetails(username: String?, password: String?): LiveData<UserLoginEntity>

    // New query to get a user by username
    @Query("SELECT * FROM user WHERE Username = :username")
    fun getUserByUsername(username: String): UserLoginEntity?
}
