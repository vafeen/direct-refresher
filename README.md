# Direct-Refresher Android library [![](https://jitpack.io/v/vafeen/Direct-Refresher.svg)](https://jitpack.io/#vafeen/Direct-Refresher)

## Preview

Direct-Refresher is an Android library for updating applications without using app markets.
It downloads APK files from a provided link and initiates the installation request for the user.

## Getting started

I'll show how to use it with dependency injection using Koin.

Firstly, define providing Downloader and Installer (and Refresher optional, if you don't want to split this process) wtih special following public functions:

```kt
internal val libModule = module {
    single<Downloader> {
        provideDownloader(context = get(), baseURL = "Yout base URL")
    }
    single<Installer> {
        provideInstaller(context = get())
    }
    single<Refresher> {
        provideRefresher(context = get(), downloader = get(), installer = get())
    }
}
```

Downloader and Refresher have the `progressFlow` property, which is used for showing the download process using the `DownloadStatus` KClass:

- `Started` - after the download starts.
- `InProgress(val percentage: Float)` - during the download, it shows the current download percentage.
- `Success` - when the download is completed successfully.
- `Error(val exception: Exception)` - when an exception occurs during the download.

To update your app, you have two options:

- Call the `refresh` function from `Refresher`, providing the coroutineScope, the URL for downloading the file, and the name under which the downloaded APK file will be saved.
- Call the `downloadFile` function from `Downloader` for subsequent installation, providing the same parameters. Optionally, you can provide a lambda that will be called after the download is successful. For example, in this lambda, you can initiate the installation process.

## Gradle implementation

Steps to implement this library in your project:

1. Add `https://jitpack.io` maven repository to your `settings.gradle.kts`

    ```kt
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
            maven(url = "https://jitpack.io") // add this line 
        }
    }
    ```

2. Add library version [![](https://jitpack.io/v/vafeen/Direct-Refresher.svg)](https://jitpack.io/#vafeen/Direct-Refresher) implementation to your `build.gradle.kts`

    ```kt
    implementation ("com.github.vafeen:Direct-Refresher:VERSION") 
    ```
