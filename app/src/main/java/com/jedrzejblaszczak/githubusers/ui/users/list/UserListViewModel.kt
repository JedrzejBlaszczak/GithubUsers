package com.jedrzejblaszczak.githubusers.ui.users.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jedrzejblaszczak.githubusers.ui.users.usecase.GetUsersListByLoginPatternUseCase
import com.jedrzejblaszczak.githubusers.ui.users.usecase.GetUsersListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class UserListViewModel(
    private val getUsersListByLoginPatternUseCase: GetUsersListByLoginPatternUseCase,
    private val getUsersListUseCase: GetUsersListUseCase,
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val users: StateFlow<List<UserListModel>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            _isLoading.value = true
            if (query.isEmpty()) {
                getUsersListUseCase()
            } else {
                getUsersListByLoginPatternUseCase(pattern = query)
            }
        }
        .onEach {
            _isLoading.value = false
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
