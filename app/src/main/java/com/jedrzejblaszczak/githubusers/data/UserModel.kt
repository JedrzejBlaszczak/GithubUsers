package com.jedrzejblaszczak.githubusers.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey val id: Int,
    val login: String,
    val avatar_url: String,
    val url: String
): java.io.Serializable
