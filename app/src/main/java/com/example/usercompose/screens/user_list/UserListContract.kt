package com.example.usercompose.screens.user_list

import com.example.usercompose.screens.entity.User

data class UserListState(val userList: List<User> = emptyList())
sealed interface UserListEvent {
    data class GetUsersFromServer(val limit: Int = 50) : UserListEvent
    data class InsertUsersToDb(val users: List<User>) : UserListEvent
    object GetUserFromDb : UserListEvent
}