package com.jedrzejblaszczak.githubusers.ui.users.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.jedrzejblaszczak.githubusers.data.UserModel
import com.jedrzejblaszczak.githubusers.ui.users.UsersViewModel

@Composable
fun UsersListScreen(navController: NavController, usersViewModel: UsersViewModel) {
    val searchQuery by usersViewModel.searchQuery.collectAsState()
    val users by usersViewModel.users.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Search Bar
        TextField(
            value = searchQuery,
            onValueChange = { query -> usersViewModel.updateSearchQuery(query) },  // Update the query in ViewModel
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search users...") }
        )

        LazyColumn {
            items(users) { user ->
                UserItem(user) {
                    navController.navigate("detail/${user.id}")
                }
            }
        }
    }
}

@Composable
fun UserItem(user: UserModel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick),
    ) {
        Image(
            painter = rememberAsyncImagePainter(user.avatar_url),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = user.login, style = MaterialTheme.typography.headlineMedium)
            Text(text = user.url, style = MaterialTheme.typography.bodySmall)
        }
    }
}
