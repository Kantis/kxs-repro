import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Note: AWS supports only JDK 11 for Lambdas so far
val targetJdk = JavaVersion.VERSION_17

plugins {
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    kotlin("plugin.spring") version "1.8.10"
}

repositories {
    mavenCentral()
    maven("https://repo.spring.io/snapshot")
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.5"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework:spring-webflux:6.0.8-SNAPSHOT")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation(kotlin("reflect")) // Without this dependency, nothing works
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.rest-assured:kotlin-extensions")
    testImplementation(libs.kotest.assertionsJson)
    testImplementation(libs.kotest.runnerJunit5)
    testImplementation(libs.kotest.datatest)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = targetJdk.toString()
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}