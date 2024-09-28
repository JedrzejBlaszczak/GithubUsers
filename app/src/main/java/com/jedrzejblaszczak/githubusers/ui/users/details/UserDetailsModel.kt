package com.jedrzejblaszczak.githubusers.ui.users.details

data class UserDetailsModel(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val url: String,
    val type: String,
)
