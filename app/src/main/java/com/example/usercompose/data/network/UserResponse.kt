package com.example.usercompose.data.network

import com.google.gson.annotations.SerializedName



data class UsersResponse(
    @SerializedName("results")
    val user: List<UserNetwork>
)


