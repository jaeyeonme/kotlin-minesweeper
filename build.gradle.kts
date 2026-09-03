plugins {
    application
    kotlin("jvm") version "2.4.10"
    id("org.jlleitschuh.gradle.ktlint") version "14.2.0"
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
    testImplementation("io.kotest:kotest-runner-junit5:5.2.3")
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

ktlint {
    verbose.set(true)
}
