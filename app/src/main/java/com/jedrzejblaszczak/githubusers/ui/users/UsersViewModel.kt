package com.jedrzejblaszczak.githubusers.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jedrzejblaszczak.githubusers.data.UserModel
import com.jedrzejblaszczak.githubusers.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class UsersViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Search results, updated whenever the search query changes
    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val users: StateFlow<List<UserModel>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                userRepository.getUsers()
            } else {
                userRepository.searchUsers(query)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }
}
