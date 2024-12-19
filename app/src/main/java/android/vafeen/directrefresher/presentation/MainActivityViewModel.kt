package android.vafeen.directrefresher.presentation

import android.vafeen.direct_refresher.refresher.Refresher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

internal class MainActivityViewModel(
    private val refresher: Refresher
) : ViewModel() {

    /**
     * Поток состояния, который отслеживает процесс обновления.
     */
    val isUpdateInProcessFlow = refresher.isUpdateInProcessFlow

    /**
     * Поток состояния, который отслеживает процент выполнения обновления.
     */
    val percentageFlow = refresher.percentageFlow

    /**
     * Функция запуска скачивания и установки обновлений
     */
    fun update() = refresher.refresh(viewModelScope, TestData.testUrl)

}