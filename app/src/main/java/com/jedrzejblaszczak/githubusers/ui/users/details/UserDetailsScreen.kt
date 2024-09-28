package com.jedrzejblaszczak.githubusers.ui.users.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.jedrzejblaszczak.githubusers.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserDetailsScreen(userId: Int) {
    val viewModel = koinViewModel<UserDetailsViewModel>()
    val user = viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUserDetails(userId)
    }

    user.value?.let {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(it.avatarUrl),
                contentDescription = it.login,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it.login, style = MaterialTheme.typography.headlineMedium)
            Text(text = it.type, style = MaterialTheme.typography.bodyMedium)
            Text(text = it.url, style = MaterialTheme.typography.bodySmall)

        }
    } ?: Text(text = stringResource(id = R.string.user_details_noUserError))
}
