package com.jedrzejblaszczak.githubusers.ui.users.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    val users by usersViewModel.users.collectAsState()

    LazyColumn {
        items(users) { user ->
            UserItem(user) {
                navController.navigate("detail/${user.id}")
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
            Text(text = user.login, style = MaterialTheme.typography.bodyMedium)
            Text(text = user.url, style = MaterialTheme.typography.headlineMedium)
        }
    }
}
