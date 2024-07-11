package com.example.agenda

import android.app.Application
import com.example.agenda.backend.di.appModule
import com.example.agenda.backend.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(
                appModule,
                storageModule
            )
        }
    }
}