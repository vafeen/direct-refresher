package android.vafeen.direct_refresher.installer

/**
 * Interface for installing files from a given file path.
 */
interface Installer {

    /**
     * Installs a file from the given file name.
     *
     * @param fileName The name of the file to be installed.
     */
    fun installAPK(fileName: String)
}
