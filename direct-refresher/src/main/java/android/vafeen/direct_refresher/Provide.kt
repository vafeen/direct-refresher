package android.vafeen.direct_refresher

import android.content.Context
import android.vafeen.direct_refresher.downloader.Downloader
import android.vafeen.direct_refresher.downloader.DownloaderImpl
import android.vafeen.direct_refresher.downloader.DownloadService
import android.vafeen.direct_refresher.installer.Installer
import android.vafeen.direct_refresher.installer.InstallerImpl
import android.vafeen.direct_refresher.refresher.Refresher
import android.vafeen.direct_refresher.refresher.RefresherImpl
import retrofit2.Retrofit

/**
 * Provides an instance of [DownloadService] using Retrofit.
 *
 * @param baseUrl The base URL for the Retrofit service.
 * @return An instance of [DownloadService].
 */
internal fun provideDownloadService(baseUrl: String): DownloadService =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .build()
        .create(DownloadService::class.java)

/**
 * Provides an instance of [Downloader] with the given [context] and [baseUrl].
 *
 * @param context The application context.
 * @param baseUrl The base URL for downloading files.
 * @return An instance of [Downloader].
 */
fun provideDownloader(context: Context, baseUrl: String): Downloader =
    DownloaderImpl(context = context, baseUrl = baseUrl)

/**
 * Provides an instance of [Installer] with the given [context].
 *
 * @param context The application context.
 * @return An instance of [Installer].
 */
fun provideInstaller(context: Context): Installer =
    InstallerImpl(context = context)

/**
 * Provides an instance of [Refresher] with the given [context], [downloader], and [installer].
 *
 * @param context The application context.
 * @param downloader The downloader instance.
 * @param installer The installer instance.
 * @return An instance of [Refresher].
 */
fun provideRefresher(
    context: Context,
    downloader: Downloader,
    installer: Installer
): Refresher =
    RefresherImpl(context = context, downloader = downloader, installer = installer)
