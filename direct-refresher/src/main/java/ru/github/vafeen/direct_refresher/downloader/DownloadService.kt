package android.vafeen.direct_refresher.downloader

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * Interface for defining API methods related to file downloads.
 */
internal interface DownloadService {

    /**
     * Downloads a file from the specified URL.
     *
     * @param fileUrl The URL of the file to download.
     * @return [Call] to execute the request and retrieve [ResponseBody].
     */
    @GET
    @Streaming // Used to download large files to avoid memory overflow.
    fun downloadFile(@Url fileUrl: String): Call<ResponseBody>
}
