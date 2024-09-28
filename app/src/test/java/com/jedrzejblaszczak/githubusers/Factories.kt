package com.jedrzejblaszczak.githubusers

import com.jedrzejblaszczak.githubusers.db.user.UserModel
import com.jedrzejblaszczak.githubusers.ui.users.details.UserDetailsModel

fun userModel(
    id: Int = 0,
    login: String = "",
    avatar_url: String = "",
    url: String = "",
    node_id: String = "",
    type: String = "",
    site_admin: Boolean = false,
) = UserModel(
    id = id,
    login = login,
    avatar_url = avatar_url,
    url = url,
    node_id = node_id,
    type = type,
    site_admin = site_admin,
)

fun userDetailsModel(
    id: Int = 0,
    login: String = "",
    avatarUrl: String = "",
    url: String = "",
    type: String = "",
) = UserDetailsModel(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    url = url,
    type = type,
)
