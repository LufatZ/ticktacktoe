plugins {
    id("java")
}

group = "de.LufatZ"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
}
val mainClass = "Main"

tasks.jar {
    manifest {
        attributes["Main-Class"] = mainClass
    }
    from(sourceSets.main.get().output)
}