buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.47")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.10")
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("io.realm.kotlin") version "1.10.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}