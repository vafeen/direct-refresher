package android.vafeen.directrefresher.presentation

import android.vafeen.direct_refresher.downloader.DownloadStatus
import android.vafeen.direct_refresher.refresher.Refresher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the [MainActivity], responsible for managing the state of the UI
 * and handling user interactions related to the app update process.
 *
 * @param refresher The [Refresher] instance used to download and install the update.
 */
internal class MainActivityViewModel(
    private val refresher: Refresher
) : ViewModel() {

    /**
     * A flow that indicates whether an update is currently in progress.
     */
    private val _isUpdateInProcessFlow = MutableStateFlow(false)
    val isUpdateInProcessFlow = _isUpdateInProcessFlow.asStateFlow()

    /**
     * A flow that emits the current download progress percentage (a float between 0.0 and 1.0).
     */
    private val _percentageFlow = MutableSharedFlow<Float>()
    val percentageFlow = _percentageFlow.asSharedFlow()

    /**
     * Initializes the ViewModel and starts observing the download progress from the [refresher].
     * It updates the UI state based on the [DownloadStatus].
     */
    init {
        viewModelScope.launch {
            refresher.progressFlow.collect { status ->
                _isUpdateInProcessFlow.emit(status is DownloadStatus.Started || status is DownloadStatus.InProgress)
                if (status is DownloadStatus.InProgress) _percentageFlow.emit(status.percentage / 100f)
            }
        }
    }

    /**
     * Initiates the application update process.
     *
     * It launches a coroutine in the IO dispatcher to call the [refresher.refresh] method
     * with test data. In a production environment, this would use actual release data.
     */
    fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            refresher.refresh(TestData.testUrl2, TestData.testName2)
        }
    }
}
