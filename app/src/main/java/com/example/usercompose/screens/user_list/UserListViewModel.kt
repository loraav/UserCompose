package com.example.usercompose.screens.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercompose.core.App
import com.example.usercompose.data.database.repository.UserRepository
import com.example.usercompose.data.network.Api
import com.example.usercompose.data.network.NetworkManager
import com.example.usercompose.data.network.toUser


import com.example.usercompose.screens.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Collections.emptyList


class UserListViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users


    private val api: Api
    private val userRepository: UserRepository

    init {
        api = NetworkManager.getRetrofit().create(Api::class.java)
        userRepository = App.getAppInstance().database
//       getUsersList()
         getUsersFromDatabase()
    }

//    private fun getUsersList() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val userList = mutableListOf<User>()
//            val userResponse = api.getUsersList(50)
//            userList.addAll(userResponse.user.map { it.toUser() })
//            insertUsers(userResponse.user.map { it.toUser() })
//
//            _users.emit(userList)
//        }
//    }

    private fun insertUsers(userList: List<User>) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertPersonsList(userList)
        }
    }

    private fun getUsersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            val users: List<User> = userRepository.getAllUsers()
            _users.emit(users)
        }
    }
}