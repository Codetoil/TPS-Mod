plugins {
    id("java-library")
    id("idea")
    id("eclipse")
    id("maven-publish")
    id("com.gradleup.shadow") version "9.4.0"
}

repositories {
    mavenCentral()
    maven {
        name = "Mojang"
        url = uri("https://libraries.minecraft.net")
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

val brigader_version: String by project
val eventbus_version: String by project
val jspecify_version: String by project

dependencies {
    api("com.mojang:brigadier:$brigader_version")
    api("org.greenrobot:eventbus-java:$eventbus_version")
    api("org.jspecify:jspecify:$jspecify_version")
}