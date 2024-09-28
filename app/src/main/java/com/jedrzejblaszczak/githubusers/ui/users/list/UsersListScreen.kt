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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.jedrzejblaszczak.githubusers.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun UsersListScreen(navController: NavController) {
    val viewModel = koinViewModel<UserListViewModel>()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val users by viewModel.users.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery,
            onValueChange = { query -> viewModel.updateSearchQuery(query) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text(text = stringResource(id = R.string.user_listScreen_searchBoxPlaceholder))
            }
        )

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn {
                items(users) { user ->
                    UserItem(user) {
                        navController.navigate("detail/${user.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(user: UserListModel, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick),
    ) {
        Image(
            painter = rememberAsyncImagePainter(user.avatarUrl),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = user.login, style = MaterialTheme.typography.headlineMedium)
    }
}
