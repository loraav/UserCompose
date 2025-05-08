package com.example.usercompose.data.database.repository

import android.content.Context

import androidx.room.Room
import com.example.usercompose.data.database.AppDatabase
import com.example.usercompose.data.database.entity.UserDatabase
import com.example.usercompose.data.database.entity.toUserDatabase
import com.example.usercompose.data.database.entity.toUser
import com.example.usercompose.screens.entity.User


class UserRepository(context: Context) {

    private val appDatabase:AppDatabase by lazy {
     Room.databaseBuilder(context,
         AppDatabase::class.java,
         "database.db").build()
 }

    suspend fun insertPersonsList(personsList: List<User>){
        appDatabase.userDao().insertUserList(personsList.map {
            it.toUserDatabase()
        })

    }

    suspend fun getAllUsers(): List<User>{
        return appDatabase.userDao().getAll().map { it.toUser() }
    }

    suspend fun getUserByID(id:Long): UserDatabase{
        return appDatabase.userDao().getUserById(id)
    }
}