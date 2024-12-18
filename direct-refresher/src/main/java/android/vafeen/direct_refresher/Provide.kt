package android.vafeen.direct_refresher

import android.content.Context
import android.vafeen.direct_refresher.downloader.DownloadFileRepository
import android.vafeen.direct_refresher.downloader.DownloadFileRepositoryImpl
import android.vafeen.direct_refresher.downloader.downloading.DownloadService
import android.vafeen.direct_refresher.installer.Installer
import android.vafeen.direct_refresher.installer.InstallerImpl
import android.vafeen.direct_refresher.refresher.Refresher
import android.vafeen.direct_refresher.refresher.RefresherImpl
import retrofit2.Retrofit

internal fun provideDownloadService(baseUrl: String) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .build().create(DownloadService::class.java)

fun provideDownloadFileRepository(baseUrl: String): DownloadFileRepository =
    DownloadFileRepositoryImpl(baseUrl)

fun provideInstaller(context: Context): Installer = InstallerImpl(context)
fun provideRefresher(
    context: Context,
    downloadFileRepository: DownloadFileRepository,
    installer: Installer
): Refresher = RefresherImpl(
    context,
    downloadFileRepository, installer
)
