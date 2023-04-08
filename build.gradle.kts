plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "com.unongmilk"
version = "1.0"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(fileTree(mapOf("dir" to "lib", "include" to listOf("*.jar"))))
}