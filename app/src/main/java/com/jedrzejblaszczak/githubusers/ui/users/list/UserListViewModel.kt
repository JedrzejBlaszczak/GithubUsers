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
import kotlinx.coroutines.flow.stateIn

class UserListViewModel(
    private val getUsersListByLoginPatternUseCase: GetUsersListByLoginPatternUseCase,
    private val getUsersListUseCase: GetUsersListUseCase,
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val users: StateFlow<List<UserListModel>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                getUsersListUseCase()
            } else {
                getUsersListByLoginPatternUseCase(pattern = query)
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
