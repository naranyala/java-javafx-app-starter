plugins {
    application
    java
}

group = "com.example"
version = "0.1.0"

repositories {
    mavenCentral()
}

val javafxVersion = "21.0.13"

/**
 * JavaFX ships native libraries per platform. Detect the build machine so the
 * correct classifier is pulled from Maven Central.
 *
 * Supported classifiers: linux, linux-aarch64, mac, mac-aarch64, win.
 */
val javafxPlatform: String = run {
    val os = System.getProperty("os.name").lowercase()
    val arch = System.getProperty("os.arch").lowercase()
    when {
        os.contains("win") -> "win"
        os.contains("mac") -> if (arch == "aarch64") "mac-aarch64" else "mac"
        arch == "aarch64" -> "linux-aarch64"
        else -> "linux"
    }
}

dependencies {
    listOf("base", "graphics", "controls", "fxml").forEach { module ->
        implementation("org.openjfx:javafx-$module:$javafxVersion:$javafxPlatform")
    }

    testImplementation(platform("org.junit:junit-bom:5.14.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Launch through a plain class, not one extending Application: JavaFX
    // otherwise refuses to start when the runtime is on the classpath.
    mainClass = "com.example.app.Launcher"
}

tasks.jar {
    // Fixed name keeps the jpackage task below simple.
    archiveFileName = "app.jar"
}

tasks.test {
    useJUnitPlatform()
}

/**
 * Builds a self-contained native app image with jpackage (bundled with JDK 21).
 * Output: build/jpackage/<name>
 *
 * Run: ./gradlew packageApp
 */
tasks.register<Exec>("packageApp") {
    group = "distribution"
    description = "Builds a native application image with jpackage."
    dependsOn(tasks.installDist)

    val installDir = layout.buildDirectory.dir("install/${rootProject.name}/lib").get().asFile
    val outputDir = layout.buildDirectory.dir("jpackage").get().asFile
    val appName = "JavaFXStarter"

    doFirst {
        outputDir.deleteRecursively()
        outputDir.mkdirs()
    }

    commandLine(
        "${System.getProperty("java.home")}/bin/jpackage",
        "--type", "app-image",
        "--name", appName,
        "--input", installDir.absolutePath,
        "--main-jar", "app.jar",
        "--main-class", "com.example.app.Launcher",
        "--dest", outputDir.absolutePath,
    )
}
