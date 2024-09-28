package com.jedrzejblaszczak.githubusers.ui.users.usecase

import com.jedrzejblaszczak.githubusers.repository.UserRepository
import com.jedrzejblaszczak.githubusers.ui.users.list.UserListModel
import com.jedrzejblaszczak.githubusers.ui.users.list.UserModelToUserListModelConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUsersListUseCase(
    private val userRepository: UserRepository,
    private val userModelToUserListModelConverter: UserModelToUserListModelConverter,
) {
    suspend operator fun invoke(): Flow<List<UserListModel>> =
        userRepository.getUsers().map { userModels ->
            userModels.map { userModel ->
                userModelToUserListModelConverter(userModel)
            }
        }
}
