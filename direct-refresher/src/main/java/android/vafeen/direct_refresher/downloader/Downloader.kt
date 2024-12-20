package android.vafeen.direct_refresher.downloader

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

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
     * @param coroutineScope The coroutine scope in which the download operation will run.
     * @param fileUrl The URL of the file to be downloaded.
     * @param downloadedFileName The name of the file to save on the device.
     * @param onFileDownloaded An optional lambda executed after the file has been successfully downloaded.
     */
    suspend fun downloadFile(
        coroutineScope: CoroutineScope,
        fileUrl: String,
        downloadedFileName: String,
        onFileDownloaded: (suspend () -> Unit)? = null
    )
}
