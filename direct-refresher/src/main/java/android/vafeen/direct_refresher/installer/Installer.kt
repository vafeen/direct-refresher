package android.vafeen.direct_refresher.installer

/**
 * Interface for installing files from a given file path.
 */
interface Installer {

    /**
     * Installs a file from the given file path.
     *
     * @param filePath The path to the file to be installed.
     */
    fun installAPK(filePath: String)
}
