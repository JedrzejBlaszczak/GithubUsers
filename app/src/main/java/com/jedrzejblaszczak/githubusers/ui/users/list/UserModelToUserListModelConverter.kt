package com.jedrzejblaszczak.githubusers.ui.users.list

import com.jedrzejblaszczak.githubusers.db.user.UserModel

class UserModelToUserListModelConverter {

    operator fun invoke(userModel: UserModel): UserListModel =
        UserListModel(
            id = userModel.id,
            login = userModel.login,
            avatarUrl = userModel.avatar_url
        )
}
