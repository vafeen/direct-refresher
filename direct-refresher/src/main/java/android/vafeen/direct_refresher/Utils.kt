package android.vafeen.direct_refresher

import android.content.Context

/**
 * Generates the full path to a file in the app's external cache directory.
 *
 * This is used to create a consistent path for storing downloaded files, such as APKs,
 * before they are installed.
 *
 * @param fileName The name of the file.
 * @return A string representing the full path to the file in the external cache directory.
 */
internal fun Context.pathToDownloadFile(fileName: String): String =
    "${externalCacheDir?.absolutePath}/$fileName"
