package android.vafeen.direct_refresher.downloader

import android.vafeen.direct_refresher.downloader.downloading.DownloadService
import android.vafeen.direct_refresher.downloader.downloading.CompositeFileStream
import android.vafeen.direct_refresher.downloader.result.ResponseResult
import android.vafeen.direct_refresher.provideDownloadService
import java.io.IOException
import java.net.UnknownHostException

/**
 * Реализация репозитория для загрузки файлов через сеть.
 *
 * @property downloadService Сервис для выполнения запросов на загрузку файлов.
 */
internal class DownloadFileRepositoryImpl(
    baseUrl: String,
) : DownloadFileRepository {
    private val downloadService: DownloadService = provideDownloadService(baseUrl)

    /**
     * Загрузка файла по указанному URL.
     *
     * @param fileUrl URL файла для загрузки.
     * @return Результат загрузки файла, содержащий поток данных или ошибку.
     */
    override suspend fun downloadFile(fileUrl: String): ResponseResult<CompositeFileStream> = try {
        // Выполнение запроса
        val call = downloadService.downloadFile(fileUrl)
        val response = call.execute() // Синхронный вызов
        val body = response.body()

        // Проверяем успешность ответа и наличие тела
        if (response.isSuccessful && body != null) {
            val contentLength = body.contentLength()
            // Возвращаем успешный результат
            ResponseResult.Success(
                CompositeFileStream(
                    stream = body.byteStream(), // Получаем InputStream из тела ответа
                    contentLength = contentLength
                )
            )
        } else {
            // Обработка HTTP ошибки, если статус не успешен
            ResponseResult.Error(Exception("Ошибка сервера: ${response.code()}"))
        }
    } catch (e: UnknownHostException) {
        // Ошибка при отсутствии интернета
        ResponseResult.Error(UnknownHostException("Нет подключения к интернету"))
    } catch (e: IOException) {
        // Общая ошибка сети
        ResponseResult.Error(IOException("Ошибка сети: ${e.localizedMessage}"))
    } catch (e: Exception) {
        // Обработка любых других ошибок
        ResponseResult.Error(Exception("Неизвестная ошибка: ${e.localizedMessage}"))
    }
}