package com.example.usercompose.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("api/")
    suspend fun getUsersList(@Query("results")userCount:Int): UsersResponse



}
