package com.example.usercompose.screens.user_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserDetailScreen(userDetailViewModel: UserDetailViewModel = viewModel(), userId: Long, onBackClick: () -> Unit) {
    val user by userDetailViewModel.user.collectAsState()


    LaunchedEffect(userId) {
        userDetailViewModel.getUserById(userId)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = user.gender, style = MaterialTheme.typography.bodyLarge)
        Text(text = user.name.title, style = MaterialTheme.typography.bodyLarge)
        Text(text = user.name.first, style = MaterialTheme.typography.bodyLarge)
        Text(text = user.name.last, style = MaterialTheme.typography.bodyLarge)
    }
}