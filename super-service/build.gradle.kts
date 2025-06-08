plugins {
    `java-library`
}

group = "org.example"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.example:super-library:1.0-SNAPSHOT")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
