package android.vafeen.directrefresher.presentation

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
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays the progress of an update.
 *
 * This component shows a text indicating the update progress in percentage and a
 * [LinearProgressIndicator] to visually represent the progress.
 *
 * @param percentage The current progress of the update, represented as a float between 0.0 and 1.0.
 */
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
