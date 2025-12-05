package io.github.vafeen.directrefresher.app

import android.app.Application
import io.github.vafeen.directrefresher.diModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(diModule)
        }
    }
}