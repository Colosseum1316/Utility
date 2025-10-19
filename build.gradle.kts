import java.net.URL

plugins {
    java
    `java-library`
    id("org.jetbrains.kotlin.jvm") version "2.1.20"
    id("org.jetbrains.dokka") version "1.8.10"
    `maven-publish`
    jacoco
}

group = "colosseum.minecraft"
version = "0.1-SNAPSHOT"

buildscript {
    apply(from = "properties.gradle.kts")
}

java {
    sourceCompatibility = JavaVersion.toVersion("${project.extra["compilation_java_version"]}")
    targetCompatibility = JavaVersion.toVersion("${project.extra["compilation_java_version"]}")
    withSourcesJar()
    withJavadocJar()
}

kotlin {
    jvmToolchain("${project.extra["compilation_java_version"]}".toInt())
}

repositories {
    mavenCentral()
    exclusiveContent {
        forRepository {
            maven("https://coffeewarehouse.harborbucket.top/snapshots")
        }
        filter {
            includeGroup("colosseum.minecraft")
            includeGroup("net.md-5")
            includeGroup("com.github.MockBukkit")
        }
    }
}

dependencies {
    implementation("colosseum.minecraft:colosseumspigot-api:1.8.8-R0.1-SNAPSHOT")

    testImplementation("colosseum.minecraft:colosseumspigot-api:1.8.8-R0.1-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${rootProject.findProperty("junit_version")}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${rootProject.findProperty("junit_version")}")
    testImplementation("com.github.MockBukkit:MockBukkit:v1.8-spigot-SNAPSHOT") {
        exclude("org.spigotmc")
    }
}

tasks.jar {
    archiveClassifier.set("original")
}

tasks.dokkaJavadoc {
    dokkaSourceSets {
        configureEach {
            jdkVersion.set("${project.extra["compilation_java_version"]}".toInt())
            externalDocumentationLink {
                url.set(URL("https://guava.dev/releases/17.0/api/docs/"))
            }
            externalDocumentationLink {
                url.set(URL("https://helpch.at/docs/1.8.8/"))
            }
        }
    }
}

tasks.named<Jar>("javadocJar") {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    from(tasks.named("dokkaJavadoc"))
}

tasks.test {
    systemProperty("mapdata.xsd", rootProject.projectDir.resolve("resources").resolve("mapdata.xsd").absolutePath)
    useJUnitPlatform()
    reports {
        html.required = false
        junitXml.required = true
        junitXml.isOutputPerTestCase = true
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        html.required = true
    }
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                include("colosseum/utility/**")
            }
        })
    )
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])

            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }

    System.getenv("COLOSSEUM_MAVEN_URL").let { mavenUrl ->
        if (mavenUrl == null) {
            return@let
        }
        println("Configuring publishing")
        repositories {
            maven {
                name = "ColosseumMaven"
                url = uri(mavenUrl)
                credentials {
                    username = System.getenv("COLOSSEUM_MAVEN_USERNAME")
                    password = System.getenv("COLOSSEUM_MAVEN_PASSWORD")
                }
            }
        }
    }
}
