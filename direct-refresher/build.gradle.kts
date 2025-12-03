plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.com.vanniktech.maven.publish)
}

group = project.findProperty("group").toString()
version = project.findProperty("version").toString()

android {
    namespace = "io.github.vafeen.direct_refresher"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    api(libs.retrofit)
    api(libs.converter.gson)
}
mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    signAllPublications()

    coordinates(
        group as String?,
        "direct-refresher",
        version as String?
    )

    pom {
        name.set("Direct Refresher library")
        description.set("Direct Refresher library")
        inceptionYear.set("2025")
        url.set("https://github.com/vafeen/direct-refresher")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("vafeen")
                name.set("A")
                url.set("https://github.com/vafeen/")
            }
        }
        scm {
            url.set("https://github.com/vafeen/direct-refresher")
            connection.set("scm:git:git://github.com/vafeen/direct-refresher.git")
            developerConnection.set("scm:git:ssh://git@github.com/vafeen/direct-refresher.git")
        }
    }
}