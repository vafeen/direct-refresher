package android.vafeen.direct_refresher.downloader

import android.content.Context
import android.util.Log
import android.vafeen.direct_refresher.DirectRefresher
import android.vafeen.direct_refresher.pathToDownloadFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.UnknownHostException

/**
 * Implementation of the Downloader interface for downloading files over the network.
 *
 * @property context The context for accessing system resources.
 * @property baseUrl The base URL for the download service.
 */
internal class DownloaderImpl(
    private val context: Context,
    baseUrl: String,
) : Downloader {

    private val downloadService: DownloadService = DirectRefresher.provideDownloadService(baseUrl)

    /**
     * A flow for tracking the status of the download process.
     */
    private val _progressFlow = MutableSharedFlow<DownloadStatus>()
    override val progressFlow = _progressFlow.asSharedFlow()

    /**
     * Downloads a file from the specified URL and saves it to a predefined location.
     *
     * @param coroutineScope The coroutine scope for managing asynchronous tasks.
     * @param fileUrl The URL of the file to download.
     * @param downloadedFileName The name to save the downloaded file as.
     * @param onFileDownloaded An optional lambda invoked when the file download is complete.
     */
    override suspend fun downloadFile(
        coroutineScope: CoroutineScope,
        fileUrl: String,
        downloadedFileName: String,
        onFileDownloaded: (suspend () -> Unit)?
    ) {
        val apkFilePath =
            context.pathToDownloadFile(downloadedFileName) // Get the path to save the file.
        coroutineScope.launch(Dispatchers.IO) {
            try {
                _progressFlow.emit(DownloadStatus.Started) // Set the download status to Started.

                // Make the download request.
                val call = downloadService.downloadFile(fileUrl)
                val response = call.execute() // Execute the request synchronously.
                val body = response.body()

                // Check if the response is successful and has a body.
                if (response.isSuccessful && body != null) {
                    val file =
                        File(apkFilePath) // Create a file object for saving the downloaded file.
                    val inputStream =
                        body.byteStream() // Get the InputStream from the response body.
                    val outputStream =
                        FileOutputStream(file) // Create an output stream for writing to the file.
                    val buffer = ByteArray(8 * 1024) // Buffer for reading data (8 KB).
                    var bytesRead: Int // Tracks the number of bytes read in each iteration.
                    var totalBytesRead: Long = 0 // Tracks the total bytes read.
                    val contentLength =
                        body.contentLength() // Total length of the content to download.
                    // Read data from the InputStream and write to the file.
                    while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                        outputStream.write(buffer, 0, bytesRead)
                        totalBytesRead += bytesRead
                        Log.d(
                            "Downloader",
                            "contentLen = $contentLength; read = $totalBytesRead; % = ${(totalBytesRead.toFloat() / contentLength)}"
                        )
                        _progressFlow.emit(
                            DownloadStatus.InProgress(
                                percentage = (totalBytesRead.toFloat() / contentLength)
                            )
                        ) // Emit the download progress.
                    }
                    outputStream.close() // Close the output stream.
                    inputStream.close() // Close the input stream.
                    _progressFlow.emit(DownloadStatus.Success) // Emit success status.
                    onFileDownloaded?.invoke() // Execute the optional callback.
                } else {
                    throw Exception("Server error: ${response.code()}")
                }
            } catch (e: UnknownHostException) {
                processCatch(UnknownHostException("No internet connection: ${e.localizedMessage}"))
            } catch (e: IOException) {
                processCatch(IOException("Network error: ${e.localizedMessage}"))
            } catch (e: Exception) {
                processCatch(Exception("Error: ${e.localizedMessage}"))
            }
        }
    }

    /**
     * Handles exceptions by logging the error and emitting an error status to the progress flow.
     *
     * @param e The exception to process.
     */
    private suspend fun processCatch(e: Exception) {
        val message = e.message.toString()
        _progressFlow.emit(DownloadStatus.Error(Exception(message)))
        Log.e("Downloader", message)
    }
}