package android.vafeen.direct_refresher.refresher

import android.content.Context
import android.vafeen.direct_refresher.downloader.Downloader
import android.vafeen.direct_refresher.downloader.DownloadStatus
import android.vafeen.direct_refresher.installer.Installer
import android.vafeen.direct_refresher.pathToDownloadFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

/**
 * Implementation of the Refresher interface for downloading and installing APK files.
 *
 * @property context The application context used for accessing system services.
 * @property downloader The downloader responsible for handling file downloads.
 * @property installer The installer responsible for installing the downloaded APK.
 */
internal class RefresherImpl(
    private val context: Context,
    private val downloader: Downloader,
    private val installer: Installer
) : Refresher {

    /**
     * Flow that emits the current status of the download process.
     * It reflects various stages of the download, such as start, progress, success, or error.
     */
    override val progressFlow = downloader.progressFlow

    /**
     * Downloads the APK file from the provided URL and installs it once the download is complete.
     *
     * @param coroutineScope The scope in which the download will be launched.
     * @param url The URL from which the APK file will be downloaded.
     * @param downloadedFileName The name under which the APK file will be saved on the device.
     */
    override suspend fun refresh(
        coroutineScope: CoroutineScope,
        url: String,
        downloadedFileName: String
    ) {
        // Start the file download
        downloader.downloadFile(coroutineScope, url, downloadedFileName) {
            // Once the download is complete, install the APK
            installer.installAPK(context.pathToDownloadFile(downloadedFileName))
        }
    }
}
