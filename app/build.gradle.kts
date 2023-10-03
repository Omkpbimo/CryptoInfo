plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.rratsygin.myapplication"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.rratsygin.myapplication"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }


        viewBinding.isEnabled = true
    
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

////    RX JAVA3
//    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
//    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")
//
////    Retrofit adapter
//    implementation ("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")
//json converter
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.2")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.0")

    val lifecycle_version = "2.6.2"
//architercture components
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version")


    val room_version = "2.5.2"

    implementation("androidx.room:room-runtime:$room_version")
//    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")


    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    implementation ("com.squareup.picasso:picasso:2.8")


    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}