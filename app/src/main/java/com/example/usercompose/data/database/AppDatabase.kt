package com.example.usercompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.usercompose.data.database.dao.UserDao
import com.example.usercompose.data.database.entity.UserDatabase


@Database(
    version = 1,
    entities = [
        UserDatabase::class
    ]

)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}