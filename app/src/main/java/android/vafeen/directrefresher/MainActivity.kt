package android.vafeen.directrefresher

import android.os.Bundle
import android.vafeen.directrefresher.presentation.MainActivityViewModel
import android.vafeen.directrefresher.presentation.UpdateAvailable
import android.vafeen.directrefresher.presentation.UpdateProgress
import android.vafeen.directrefresher.ui.theme.MainTheme
import android.vafeen.directrefresher.ui.theme.Theme
import android.vafeen.directrefresher.ui.theme.mainDarkColor
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val vModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isUpdateInProgress by vModel.isUpdateInProcessFlow.collectAsState(initial = false)
            val percentageFlow by vModel.percentageFlow.collectAsState(initial = 0f)
            MainTheme {
                Scaffold(containerColor = Theme.colors.singleTheme,
                    bottomBar = {
                        BottomAppBar(containerColor = mainDarkColor) {}
                    }) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        if (isUpdateInProgress) UpdateProgress(percentageFlow)
                        else UpdateAvailable { vModel.update() }
                    }
                }
            }
        }
    }
}
