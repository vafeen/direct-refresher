package android.vafeen.direct_refresher

import android.content.Context

/**
 * Extension function to generate the full path to the APK file that will be downloaded to the app's cache.
 *
 * @param fileName The name of the APK file.
 * @return A string representing the full path to the file in the application's external cache directory.
 */
internal fun Context.pathToDownloadFile(fileName: String): String =
    "${externalCacheDir?.absolutePath}/$fileName"
