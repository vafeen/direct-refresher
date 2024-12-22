package android.vafeen.directrefresher

import android.vafeen.direct_refresher.DirectRefresher
import android.vafeen.direct_refresher.downloader.Downloader
import android.vafeen.direct_refresher.installer.Installer
import android.vafeen.direct_refresher.refresher.Refresher
import android.vafeen.directrefresher.presentation.MainActivityViewModel
import android.vafeen.directrefresher.presentation.TestData
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val libModule = module {
    single<Downloader> {
        DirectRefresher.provideDownloader(context = get(), baseUrl = TestData.testBaseUrl)
    }
    single<Installer> {
        DirectRefresher.provideInstaller(context = get())
    }
    single<Refresher> {
        DirectRefresher.provideRefresher(context = get(), downloader = get(), installer = get())
    }
}
internal val diModule = module {
    viewModelOf(::MainActivityViewModel)
    includes(libModule)
}

