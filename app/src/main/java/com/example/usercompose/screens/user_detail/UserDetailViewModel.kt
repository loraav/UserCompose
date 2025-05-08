package com.example.usercompose.screens.user_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercompose.core.App
import com.example.usercompose.data.database.entity.toUser
import com.example.usercompose.data.database.repository.UserRepository
import com.example.usercompose.data.network.Api

import com.example.usercompose.data.network.NetworkManager

import com.example.usercompose.screens.entity.Name

import com.example.usercompose.screens.entity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserDetailViewModel: ViewModel() {
    private val _user = MutableStateFlow(User("", Name("","","")))
    val user: StateFlow<User> = _user
    private val userRepository: UserRepository
    private val api: Api

    init {
        api = NetworkManager.getRetrofit().create(Api::class.java)
        userRepository = App.getAppInstance().database
    }
    fun getUserById(id:Long){
        viewModelScope.launch {
            val usersResponse = userRepository.getUserByID(id)
            _user.emit(usersResponse.toUser())
        }
    }
}