package com.jedrzejblaszczak.githubusers.ui.users

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jedrzejblaszczak.githubusers.ui.AppNavGraph
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersActivity : ComponentActivity() {
    private val usersViewModel: UsersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavGraph(usersViewModel)
        }
    }
}