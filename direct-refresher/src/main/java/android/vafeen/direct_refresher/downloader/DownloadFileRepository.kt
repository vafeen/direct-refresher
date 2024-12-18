package android.vafeen.direct_refresher.downloader

import android.vafeen.direct_refresher.downloader.downloading.CompositeFileStream
import android.vafeen.direct_refresher.downloader.result.ResponseResult


/**
 * Интерфейс репозитория для загрузки файлов.
 */
interface DownloadFileRepository {

    /**
     * Загружает файл по указанному URL.
     *
     * @param fileUrl URL файла, который нужно загрузить.
     * @return [ResponseResult] с результатом загрузки, содержащим объект [CompositeFileStream].
     */
    suspend fun downloadFile(fileUrl: String): ResponseResult<CompositeFileStream>
}
