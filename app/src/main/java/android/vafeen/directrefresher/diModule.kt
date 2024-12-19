package android.vafeen.directrefresher

import android.vafeen.direct_refresher.downloader.DownloadFileRepository
import android.vafeen.direct_refresher.installer.Installer
import android.vafeen.direct_refresher.provideDownloadFileRepository
import android.vafeen.direct_refresher.provideInstaller
import android.vafeen.direct_refresher.provideRefresher
import android.vafeen.direct_refresher.refresher.Refresher
import android.vafeen.directrefresher.presentation.MainActivityViewModel
import android.vafeen.directrefresher.presentation.TestData
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val libModule = module {
    single<DownloadFileRepository> {
        provideDownloadFileRepository(baseUrl = TestData.testBaseUrl)
    }
    single<Installer> {
        provideInstaller(context = get())
    }
    single<Refresher> {
        provideRefresher(context = get(), get(), get())
    }
}
internal val diModule = module {
    viewModelOf(::MainActivityViewModel)
    includes(libModule)
}

