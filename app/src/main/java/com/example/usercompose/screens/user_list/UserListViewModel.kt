package com.example.usercompose.screens.user_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercompose.core.App
import com.example.usercompose.data.database.repository.UserRepository
import com.example.usercompose.data.network.Api
import com.example.usercompose.data.network.NetworkManager
import com.example.usercompose.data.network.toUser
import com.example.usercompose.screens.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class UserListViewModel : ViewModel() {
    private val _state = MutableStateFlow(UserListState())
    val state: StateFlow<UserListState> = _state
    private val _event = MutableSharedFlow<UserListEvent>()
    val event: SharedFlow<UserListEvent> = _event

    private val api: Api
    private val userRepository: UserRepository

    init {
        api = NetworkManager.getRetrofit().create(Api::class.java)
        userRepository = App.getAppInstance().database
        _event.onEach { event ->
            onEvent(event)
        }.shareIn(scope=viewModelScope, started = SharingStarted.Eagerly)
        sendEvent(UserListEvent.GetUsersFromServer())
    }

    private fun onEvent(event: UserListEvent) {
        when (event) {
            UserListEvent.GetUserFromDb -> {
                Log.d("qwerty","event GetUserFromDb")
                getUsersFromDatabase()
            }

            is UserListEvent.GetUsersFromServer -> {
                Log.d("qwerty","event GetUsersFromServer")
                getUsersList()
            }

            is UserListEvent.InsertUsersToDb -> {
                Log.d("qwerty","event InsertUsersToDb")
                insertUsers(event.users)
            }
        }
    }

    private fun getUsersList() {
        viewModelScope.launch(Dispatchers.IO) {
            val userResponse = api.getUsersList(50)
            Log.d("qwerty","receive userResponse, update state ")
            update { state -> state.copy(userList = userResponse.user.map { it.toUser() }) }
            Log.d("qwerty","call send event InsertUsersToDb ")
            sendEvent(UserListEvent.InsertUsersToDb(users = userResponse.user.map { it.toUser() }))

        }
    }

    private fun insertUsers(userList: List<User>) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("qwerty","try to insert")
            userRepository.insertPersonsList(userList)
        }
    }

    private fun getUsersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            val users: List<User> = userRepository.getAllUsers()
            update { state -> state.copy(userList = users) }
        }
    }

    private fun sendEvent(event: UserListEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    private fun update(updater: (UserListState) -> UserListState) = _state.update(updater)

}