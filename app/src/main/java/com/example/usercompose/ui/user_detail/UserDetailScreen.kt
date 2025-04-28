package com.example.usercompose.ui.user_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.usercompose.network.Api

@Composable
fun UserDetailScreen(userDetailViewModel: UserDetailViewModel = viewModel(), userId: Long, onBackClick: () -> Unit) {
    val user by userDetailViewModel.user.collectAsState()


    LaunchedEffect(userId) {
        userDetailViewModel.getUserById(userId)
    }

}