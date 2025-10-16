package android.vafeen.direct_refresher.refresher

import android.vafeen.direct_refresher.downloader.DownloadStatus
import kotlinx.coroutines.flow.Flow

/**
 * Interface for refreshing Android applications by downloading and installing APK files.
 *
 * This component orchestrates the download and installation process for application updates.
 */
interface Refresher {

    /**
     * A flow that broadcasts the progress of the download process.
     * It emits [DownloadStatus] updates, such as started, in-progress, success, or error,
     * allowing the UI to reflect the current state of the download.
     */
    val progressFlow: Flow<DownloadStatus>

    /**
     * Starts the download of an APK file from the provided URL and installs it once downloaded.
     *
     * This function handles the entire refresh process, from downloading the file to initiating
     * the installation. The progress of the download can be observed via the [progressFlow].
     *
     * @param url The URL of the APK file to be downloaded.
     * @param fileName The name under which the downloaded APK file will be saved.
     */
    suspend fun refresh(url: String, fileName: String)
}
