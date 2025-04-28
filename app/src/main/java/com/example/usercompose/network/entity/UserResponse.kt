package com.example.usercompose.network.entity

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val users: List<User>
)
data class UsersResponse(
    @SerializedName("data")
    val user: User
)


data class User(
    val id:Long,
    val email: String,
    @SerializedName("first_name")
    val  firstName : String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)