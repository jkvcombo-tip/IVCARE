package com.example.ivcare.userlogindatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserLoginEntity::class), version = 1, exportSchema = false)
abstract class UserLoginDatabase : RoomDatabase() {

    abstract fun userDao() : UserLoginDao

    companion object {

        @Volatile
        private var INSTANCE: UserLoginDatabase? = null

        fun getDataseClient(context: Context) : UserLoginDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, UserLoginDatabase::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}