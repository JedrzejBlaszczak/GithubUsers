package com.jedrzejblaszczak.githubusers.ui.users.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.jedrzejblaszczak.githubusers.ui.users.UsersViewModel

@Composable
fun UserDetailsScreen(userId: Int, viewModel: UsersViewModel) {
//    val user = viewModel.users.collectAsState().value.find { it.id == userId }
//    user?.let {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Image(
//                painter = rememberAsyncImagePainter(it.avatarUrl),
//                contentDescription = null,
//                modifier = Modifier.size(100.dp).clip(CircleShape)
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(text = it.login, style = MaterialTheme.typography.bodyMedium)
//            Text(text = it.url, style = MaterialTheme.typography.headlineMedium)
//        }
//    } ?: Text("User not found")
}
