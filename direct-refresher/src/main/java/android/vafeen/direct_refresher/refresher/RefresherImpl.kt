package android.vafeen.direct_refresher.refresher

import android.content.Context
import android.vafeen.direct_refresher.downloader.Downloader
import android.vafeen.direct_refresher.downloader.DownloadStatus
import android.vafeen.direct_refresher.installer.Installer
import kotlinx.coroutines.flow.Flow

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
    override val progressFlow: Flow<DownloadStatus> = downloader.progressFlow

    /**
     * Downloads the APK file from the provided URL and installs it once the download is complete.
     *
     * @param url The URL from which the APK file will be downloaded.
     * @param fileName The name under which the APK file will be saved on the device.
     */
    override suspend fun refresh(
        url: String,
        fileName: String
    ) {
        // Start the file download
        val success = downloader.downloadFile(url, fileName)
        // Once the download is complete, install the APK
        if (success) {
            installer.installAPK(fileName)
        }
    }
}
