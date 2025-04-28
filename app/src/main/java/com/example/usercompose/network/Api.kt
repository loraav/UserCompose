package com.example.usercompose.network

import com.example.usercompose.network.entity.UserResponse
import com.example.usercompose.network.entity.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("api/users")
    suspend fun getUsersList(@Query("page")page:Int): UserResponse

    @GET("api/users/{id}")
    suspend fun  getUserById(@Path("id")id:Long): UsersResponse


}
