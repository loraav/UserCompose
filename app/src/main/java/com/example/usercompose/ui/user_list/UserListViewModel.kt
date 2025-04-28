package com.example.usercompose.ui.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercompose.network.Api
import com.example.usercompose.network.NetworkManager
import com.example.usercompose.network.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserListViewModel: ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users
    private val api: Api

    init {
        api = NetworkManager.getRetrofit().create(Api::class.java)
        getUsersList()
    }

    private fun getUsersList() {
        viewModelScope.launch(Dispatchers.IO) {
            var page = 1
            val userList = mutableListOf<User>()
            val userResponse = api.getUsersList(page)
            userList.addAll(userResponse.users)
            while (page < userResponse.totalPages) {
                page++
                val nextPageUsers = api.getUsersList(page)
                userList.addAll(nextPageUsers.users)
            }
            _users.emit(userList)
        }
    }
}