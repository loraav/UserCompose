package com.example.usercompose.screens.user_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserListScreen(userListViewModel: UserListViewModel = viewModel()) {
    val state by userListViewModel.state.collectAsState()

    LazyColumn( modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.userList){ user ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = user.gender, style = MaterialTheme.typography.bodyLarge)
                    Text(text = user.name.title, style = MaterialTheme.typography.bodyLarge)
                    Text(text = user.name.first, style = MaterialTheme.typography.bodyLarge)
                    Text(text = user.name.last, style = MaterialTheme.typography.bodyLarge)
                }
//                AsyncImage(
//                    model = user.avatar,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(100.dp)
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Crop
//                )
            }

        }
    }


}