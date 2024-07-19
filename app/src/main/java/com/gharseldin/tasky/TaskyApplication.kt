package com.gharseldin.tasky

import android.app.Application
import com.gharseldin.authentication.data.di.authDataModule
import com.gharseldin.authentication.presentation.di.authViewModelModule
import com.gharseldin.tasky.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class TaskyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@TaskyApplication)
            modules(
                appModule,
                authViewModelModule,
                authDataModule
            )
        }
    }
}
