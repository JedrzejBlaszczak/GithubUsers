package com.jedrzejblaszczak.githubusers.ui.users.details

import com.jedrzejblaszczak.githubusers.db.user.UserModel

class UserModelToUserDetailsModelConverter {

    operator fun invoke(userModel: UserModel): UserDetailsModel =
        UserDetailsModel(
            id = userModel.id,
            login = userModel.login,
            avatarUrl = userModel.avatar_url,
            url = userModel.url,
            type = userModel.type,
            )
}
