// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
id ("com.android.application")version "7.2.2" apply false
    id ("com.android.library") version "7.2.2" apply false
}
buildscript {

    repositories {
        // Make sure that you have the following two repositories
        google()  // Google's Maven repository
        mavenCentral()  // Maven Central repository
    }

    dependencies {


        // Add the Maven coordinates and latest version of the plugin
        classpath ("com.google.gms:google-services:4.3.15")
        classpath("com.android.tools.build:gradle:8.1.4")
    }
}

allprojects {

    repositories {
        // Make sure that you have the following two repositories
        google()  // Google's Maven repository
        mavenCentral()  // Maven Central repository
    }
}