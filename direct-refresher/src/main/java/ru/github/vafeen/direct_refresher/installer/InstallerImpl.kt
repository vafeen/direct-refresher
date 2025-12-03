package android.vafeen.direct_refresher.installer

import android.content.Context
import android.content.Intent
import android.util.Log
import android.vafeen.direct_refresher.pathToDownloadFile
import androidx.core.content.FileProvider
import java.io.File

/**
 * Implementation of the [Installer] for installing APK files.
 *
 * This class requires a `<provider>` element to be configured in the `AndroidManifest.xml`
 * for [FileProvider] to work correctly.
 *
 * @property context The application context.
 */
internal class InstallerImpl(private val context: Context) : Installer {

    /**
     * Installs an APK file from the given file name.
     *
     * It constructs the full path to the file, and if it exists, initiates an
     * installation intent. The installation requires a `FileProvider` to be correctly
     * configured in the app's `AndroidManifest.xml`.
     *
     * @param fileName The name of the file to be installed.
     */
    override fun installAPK(fileName: String) {
        val apkFilePath = context.pathToDownloadFile(fileName)
        val file = File(apkFilePath)

        if (file.exists()) {
            val authority = "${context.packageName}.provider"
            val fileUri = FileProvider.getUriForFile(context, authority, file)

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(fileUri, "application/vnd.android.package-archive")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } else {
            Log.e("Installer", "APK file does not exist: $apkFilePath")
        }
    }
}
