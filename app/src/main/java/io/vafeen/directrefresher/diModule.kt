package io.vafeen.directrefresher

import io.vafeen.direct_refresher.DirectRefresher
import io.vafeen.direct_refresher.downloader.Downloader
import io.vafeen.direct_refresher.installer.Installer
import io.vafeen.direct_refresher.refresher.Refresher
import io.vafeen.directrefresher.presentation.MainActivityViewModel
import io.vafeen.directrefresher.presentation.TestData
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val libModule = module {
    single<Downloader> {
        DirectRefresher.provideDownloader(context = get(), baseUrl = TestData.testBaseUrl2)
    }
    single<Installer> {
        DirectRefresher.provideInstaller(context = get())
    }
    single<Refresher> {
        DirectRefresher.provideRefresher(downloader = get(), installer = get())
    }
}
internal val diModule = module {
    viewModelOf(::MainActivityViewModel)
    includes(libModule)
}

