plugins {
    id("java")
    id("application")
}

application {
    mainClass = "io.github.pallavjain01.calculator.CalculatorMain"
}


group = "io.github.pallavjain01"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "io.github.pallavjain01.CalculatorMain"
    }
}