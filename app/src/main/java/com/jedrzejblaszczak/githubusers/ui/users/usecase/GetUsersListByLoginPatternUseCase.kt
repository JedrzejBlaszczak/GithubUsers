package com.jedrzejblaszczak.githubusers.ui.users.usecase

import com.jedrzejblaszczak.githubusers.repository.UserRepository
import com.jedrzejblaszczak.githubusers.ui.users.list.UserListModel
import com.jedrzejblaszczak.githubusers.ui.users.list.UserModelToUserListModelConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUsersListByLoginPatternUseCase(
    private val userRepository: UserRepository,
    private val userModelToUserListModelConverter: UserModelToUserListModelConverter,
) {
    suspend operator fun invoke(pattern: String): Flow<List<UserListModel>> =
        userRepository.getUsersByLoginPattern(pattern).map { userModels ->
            userModels.map { userModel ->
                userModelToUserListModelConverter(userModel)
            }
        }
}
