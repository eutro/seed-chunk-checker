plugins {
    java
    id("fabric-loom")
    id("com.github.johnrengelman.shadow").version("6.1.0")
}

group = "eutro"
version = "0.1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.16.1")
    mappings("net.fabricmc:yarn:1.16.1+build.1:v2")
    modCompileOnly("net.fabricmc:fabric-loader:0.11.0") // pulls in the rest of the deps
}

tasks.shadowJar {
    from({
        zipTree(sourceSets
            .main
            .get()
            .runtimeClasspath
            .filter { it.name.contains("minecraft") }
            .singleFile)
    }) { include { it.file.name.toLowerCase().startsWith("log4j") } }
    manifest {
        attributes("Main-Class" to "eutro.seed_chunk_checker.SeedChunkChecker")
    }
}
