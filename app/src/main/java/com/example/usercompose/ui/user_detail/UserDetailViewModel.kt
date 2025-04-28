package com.example.usercompose.ui.user_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercompose.network.Api
import com.example.usercompose.network.NetworkManager
import com.example.usercompose.network.entity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserDetailViewModel: ViewModel() {
    private val _user = MutableStateFlow(User(0L,"","","",""))
    val user: StateFlow<User> = _user
    private val api: Api

    init {
        api = NetworkManager.getRetrofit().create(Api::class.java)

    }
    fun getUserById(id:Long){
        viewModelScope.launch {
            val usersResponse = api.getUserById(id)
            _user.emit(usersResponse.user)
        }
    }
}