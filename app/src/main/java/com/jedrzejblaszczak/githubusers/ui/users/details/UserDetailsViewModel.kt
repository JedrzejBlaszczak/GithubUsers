package com.jedrzejblaszczak.githubusers.ui.users.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jedrzejblaszczak.githubusers.ui.users.usecase.GetUserDetailsByIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val getUserDetailsByIdUseCase: GetUserDetailsByIdUseCase,
) : ViewModel() {

    private val _user = MutableStateFlow<UserDetailsModel?>(null)
    val user: StateFlow<UserDetailsModel?> = _user

    fun loadUserDetails(userId: Int) {
        viewModelScope.launch {
            getUserDetailsByIdUseCase(userId)
                .catch {
                    _user.value = null
                }
                .collect { userDetails ->
                    _user.value = userDetails
                }
        }
    }
}
