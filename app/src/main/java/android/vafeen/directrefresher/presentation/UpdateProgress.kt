package android.vafeen.directrefresher.presentation

import android.vafeen.directrefresher.R
import android.vafeen.directrefresher.ui.theme.FontSize
import android.vafeen.directrefresher.ui.theme.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
internal fun UpdateProgress(percentage: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(Theme.colors.buttonColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 3.dp),
            text = "Updating... ${(percentage * 100).toInt()}%",
            color = Theme.colors.oppositeTheme,
            fontSize = FontSize.medium19
        )

        LinearProgressIndicator(
            color = Theme.colors.oppositeTheme,
            trackColor = Theme.colors.singleTheme,
            progress = { percentage },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}