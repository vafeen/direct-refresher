package android.vafeen.direct_refresher.installer

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File

/**
 * Implementation of the [Installer] for installing files.
 *
 * @property context The application context.
 */
internal class InstallerImpl(private val context: Context) : Installer {

    /**
     * Installs a file from the given file path.
     *
     * Retrieves the path to the file and initiates the installation process if the file exists.
     *
     * @param filePath The path to the file to be installed.
     */
    override fun installAPK(filePath: String) {
        // Create a File object for the file at the given path
        val file = File(filePath)

        // Check if the file exists
        if (file.exists()) {
            // Create an Intent to install the file
            val intent = Intent(Intent.ACTION_VIEW).apply {
                // Set URI and MIME type for the file
                setDataAndType(
                    FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.provider", // Specify the FileProvider authority
                        file
                    ),
                    "application/*" // MIME type for all file types
                )
                // Add a flag to grant read URI permission
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                // Add a flag to start a new task
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            // Start the activity to install the file
            context.startActivity(intent)
        } else {
            // Log an error if the file does not exist
            Log.e("Installer", "File does not exist: $filePath")
        }
    }
}
