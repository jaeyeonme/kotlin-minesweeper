plugins {
    application
    kotlin("jvm") version "2.4.10"
    id("org.jmailen.kotlinter") version "5.7.0"
}

group = "camp.nextstep.edu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("minesweeper.MinesweeperApplicationKt")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.8.2")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.register("ktlintCheck") {
    group = "verification"
    description = "Runs ktlint on all Kotlin sources."
    dependsOn("lintKotlin")
}

kotlinter {
    ktlintVersion = "1.8.0"
    reporters = arrayOf("plain")
}
