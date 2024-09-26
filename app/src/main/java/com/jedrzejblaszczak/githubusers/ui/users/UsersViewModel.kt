package com.jedrzejblaszczak.githubusers.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jedrzejblaszczak.githubusers.data.UserModel
import com.jedrzejblaszczak.githubusers.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class UsersViewModel(private val repository: UserRepository) : ViewModel() {
    val users: StateFlow<List<UserModel>> = repository.getUsers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
