package android.vafeen.direct_refresher

import android.content.Context

/**
 * Эта функция формирует полный путь к файлу APK, который будет загружен в кэш приложения.
 *
 * @return Путь к файлу APK для загрузки релиза. Возвращает строку, представляющую путь к файлу.
 */
internal fun Context.pathToDownloadRelease(): String =
    "${externalCacheDir?.absolutePath}/app-release.apk"