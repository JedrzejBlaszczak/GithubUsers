package com.jedrzejblaszczak.githubusers.di

import androidx.room.Room
import com.jedrzejblaszczak.githubusers.api.UserApiServiceImpl
import com.jedrzejblaszczak.githubusers.api.UsersApiService
import com.jedrzejblaszczak.githubusers.db.AppDatabase
import com.jedrzejblaszczak.githubusers.db.user.UserDao
import com.jedrzejblaszczak.githubusers.repository.UserRepository
import com.jedrzejblaszczak.githubusers.ui.users.details.UserDetailsViewModel
import com.jedrzejblaszczak.githubusers.ui.users.details.UserModelToUserDetailsModelConverter
import com.jedrzejblaszczak.githubusers.ui.users.list.UserListViewModel
import com.jedrzejblaszczak.githubusers.ui.users.list.UserModelToUserListModelConverter
import com.jedrzejblaszczak.githubusers.ui.users.usecase.GetUserDetailsByIdUseCase
import com.jedrzejblaszczak.githubusers.ui.users.usecase.GetUsersListByLoginPatternUseCase
import com.jedrzejblaszczak.githubusers.ui.users.usecase.GetUsersListUseCase
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "github.db").build() }
    single<UserDao> { get<AppDatabase>().userDao() }

    single<UsersApiService> { UserApiServiceImpl(get()) }

    single { UserRepository(get(), get()) }

    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }

    single { UserModelToUserDetailsModelConverter() }
    single { UserModelToUserListModelConverter() }

    single { GetUsersListUseCase(get(), get()) }
    single { GetUsersListByLoginPatternUseCase(get(), get()) }
    single { GetUserDetailsByIdUseCase(get(), get()) }

    viewModel { UserListViewModel(get(), get()) }
    viewModel { UserDetailsViewModel(get()) }
}
