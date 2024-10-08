package com.jedrzejblaszczak.githubusers

import android.app.Application
import com.jedrzejblaszczak.githubusers.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}
