package android.vafeen.direct_refresher.refresher

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow

/**
 * Интерфейс для загрузки файлов, например, APK.
 */
interface Refresher {

    /**
     * Поток, представляющий процент завершения загрузки.
     */
    val percentageFlow: SharedFlow<Float>

    /**
     * Поток, указывающий, находится ли процесс обновления в процессе.
     */
    val isUpdateInProcessFlow: SharedFlow<Boolean>

    /**
     * Запускает загрузку APK-файла по указанному URL.
     *
     * @param url URL файла APK, который нужно загрузить.
     */
    fun refresh(coroutineScope: CoroutineScope, url: String)
}