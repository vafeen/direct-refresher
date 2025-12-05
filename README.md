# Direct-Refresher Android library [![GitHub Tag](https://img.shields.io/github/v/tag/vafeen/direct-refresher)](https://github.com/vafeen/direct-refresher/releases/latest/)
## Preview

Direct-Refresher is an Android library for updating applications without using app markets.
It downloads APK files from a provided link and initiates the installation request for the user.

## Getting started

I'll show how to use it with dependency injection using Koin.

Firstly, define providing Downloader and Installer (and Refresher optional, if you don't want to split this process) with special following public functions in base DirectResresher object:

```kt
internal val libModule = module {
    single<Downloader> {
        DirectResresher.provideDownloader(context = get(), baseURL = "Your base URL")
    }
    single<Installer> {
        DirectResresher.provideInstaller(context = get())
    }
    single<Refresher> {
        DirectResresher.provideRefresher(downloader = get(), installer = get())
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

## Implementation

[![GitHub Tag](https://img.shields.io/github/v/tag/vafeen/direct-refresher)](https://github.com/vafeen/direct-refresher/releases/latest/)

Gradle:

```kotlin
implementation("io.github.vafeen:direct-refresher:VERSION")
```

Other:

https://central.sonatype.com/artifact/io.github.vafeen/direct-refresher
