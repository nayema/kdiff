import org.junit.platform.gradle.plugin.FiltersExtension
import org.junit.platform.gradle.plugin.JUnitPlatformExtension

plugins {
    kotlin("jvm") version "1.1.51"
}

buildscript {
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0")
    }
}

apply {
    plugin("org.junit.platform.gradle.plugin")
}

configure<JUnitPlatformExtension> {
    filters {
        engines {
            include("spek")
        }
    }
}

dependencies {
    compile(kotlin("stdlib"))
    testCompile(kotlin("reflect"))

    testCompile("org.junit.platform:junit-platform-runner:1.0.1")
    testCompile("org.jetbrains.spek:spek-api:1.1.5")
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")

    testCompile("com.winterbe:expekt:0.2.0")
}

repositories {
    mavenCentral()
}

fun JUnitPlatformExtension.filters(setup: FiltersExtension.() -> Unit) {
    when (this) {
        is ExtensionAware -> extensions.getByType(FiltersExtension::class.java).setup()
        else -> throw Exception("${this::class} must be an instance of ExtensionAware")
    }
}
