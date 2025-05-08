package com.example.usercompose.screens.entity


data class User(
    val gender:String,
    val name:Name
)
data class Name(
    val title:String,
    val first:String,
    val last:String
)