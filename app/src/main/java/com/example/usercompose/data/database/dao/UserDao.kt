package com.example.usercompose.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.usercompose.data.database.entity.UserDatabase



@Dao

interface UserDao {
    @Insert
    suspend fun insertUserList(userList: List<UserDatabase>)

    @Query("SELECT * FROM user")
   suspend fun getAll(): List<UserDatabase>

   @Query("SELECT * FROM user WHERE id LIKE :id")
   suspend fun getUserById(id:Long): UserDatabase
}