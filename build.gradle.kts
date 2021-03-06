import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.7.0"
    application
    kotlin("plugin.serialization") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.beryx.runtime") version "1.12.7"
}

group = "jp.ac.kisarazu.22s"
version = "0.1"

repositories {
    mavenCentral()
}

kotlin {

    sourceSets {
        dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")
            implementation("org.eclipse.jgit:org.eclipse.jgit:6.1.0.202203080745-r")
            implementation("org.eclipse.jgit:org.eclipse.jgit.ssh.apache:6.1.0.202203080745-r")
            implementation ("net.mm2d.mmupnp:mmupnp:3.1.3")

            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

runtime {

    additive.set(true)
    jpackage {
        val currentOs = org.gradle.internal.os.OperatingSystem.current()
       // skipInstaller=true

        imageName="mstools"
        mainClass="MainKt"
        when{
            currentOs.isWindows->{
                imageOptions = listOf("--win-console")
            }
            currentOs.isLinux->{

            }
        }
    }
}