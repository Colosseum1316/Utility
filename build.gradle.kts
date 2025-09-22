plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.20"
    id("org.jetbrains.dokka") version "1.8.10"
    `maven-publish`
    jacoco
}

group = "colosseum.minecraft"
version = "0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
    withJavadocJar()
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    exclusiveContent {
        forRepository {
            maven("https://coffeewarehouse.harborbucket.top/snapshots")
        }
        filter {
            includeGroup("colosseum.minecraft")
            includeGroup("net.md-5")
        }
    }
}

dependencies {
    compileOnly("colosseum.minecraft:colosseumspigot-api:1.8.8-R0.1-SNAPSHOT")

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

tasks.named<Jar>("javadocJar") {
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
