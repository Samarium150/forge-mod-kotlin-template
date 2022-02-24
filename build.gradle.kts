import java.text.SimpleDateFormat
import java.util.Date

buildscript {
    repositories {
        maven("https://maven.minecraftforge.net")
        mavenCentral()
    }
    dependencies {
        classpath("net.minecraftforge.gradle:ForgeGradle:5.1.+") {
            isChanging = true
        }
    }
}

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    java
}
apply(plugin="net.minecraftforge.gradle")

group = "org.example"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://thedarkcolour.github.io/KotlinForForge/")
}

dependencies {
    "minecraft"("net.minecraftforge:forge:1.16.5-36.2.28")
    implementation("thedarkcolour:kotlinforforge:1.16.0")
}

val Project.minecraft: net.minecraftforge.gradle.common.util.MinecraftExtension
    get() = extensions.getByType()

minecraft.let {
    it.mappings("official", "1.16.5")
    it.runs {
        create("client") {
            workingDirectory(project.file("run"))
            property("forge.logging.console.level", "debug")
            mods {
                this.create("example-mod") {
                    source(sourceSets.main.get())
                }
            }
        }
        create("server") {
            workingDirectory(project.file("run"))
            property("forge.logging.console.level", "debug")
            mods {
                this.create("example-mod") {
                    source(sourceSets.main.get())
                }
            }
        }
    }
}

tasks {
    withType<Jar> {
        archiveBaseName.set("example-mod")
        manifest {
            attributes(mapOf(
                "Specification-Title" to project.name,
                "Specification-Vendor" to "author",
                "Specification-Version" to "1",
                "Implementation-Title" to project.name,
                "Implementation-Vendor" to "author",
                "Implementation-Version" to project.version,
                "Implementation-Timestamp" to SimpleDateFormat("yyyy-MM-dd").format(Date())
            ))
        }
        finalizedBy("reobfJar")
    }
}
