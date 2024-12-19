package android.vafeen.directrefresher.app

import android.app.Application
import android.vafeen.directrefresher.diModule
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