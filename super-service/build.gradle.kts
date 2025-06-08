plugins {
    `java-library`
}

group = "org.example"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.example:super-library")
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.test {
    useJUnitPlatform()
}
