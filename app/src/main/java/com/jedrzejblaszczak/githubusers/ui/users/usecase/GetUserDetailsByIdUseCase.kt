package com.jedrzejblaszczak.githubusers.ui.users.usecase

import com.jedrzejblaszczak.githubusers.repository.UserRepository
import com.jedrzejblaszczak.githubusers.ui.users.details.UserDetailsModel
import com.jedrzejblaszczak.githubusers.ui.users.details.UserModelToUserDetailsModelConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUserDetailsByIdUseCase(
    private val userRepository: UserRepository,
    private val userModelToUserDetailsModelConverter: UserModelToUserDetailsModelConverter,
) {
    suspend operator fun invoke(id: Int): Flow<UserDetailsModel?> =
        userRepository.getUserById(id).map { userModel ->
            userModelToUserDetailsModelConverter(
                userModel ?: throw IllegalStateException("UserId does not exist in DB")
            )
        }
}
