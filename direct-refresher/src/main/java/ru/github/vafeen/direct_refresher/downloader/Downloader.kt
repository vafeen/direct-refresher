package android.vafeen.direct_refresher.downloader

import kotlinx.coroutines.flow.Flow

/**
 * Interface for a file downloader repository.
 */
interface Downloader {

    /**
     * A flow that emits the download progress status, including start, in-progress updates,
     * successful completion, or any errors encountered.
     */
    val progressFlow: Flow<DownloadStatus>

    /**
     * Downloads a file from the specified URL.
     *
     * @param fileUrl The URL of the file to be downloaded.
     * @param downloadedFileName The name of the file to save on the device.
     * @return `true` if the download was successful, `false` otherwise.
     */
    suspend fun downloadFile(
        fileUrl: String,
        downloadedFileName: String,
    ): Boolean
}
