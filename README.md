# JavaFX Starter

A practical desktop-app starter built with Java 21, JavaFX, FXML/CSS, Gradle Kotlin DSL, JUnit 5, and `jpackage`. It provides a small but real app shell (home/settings navigation, persisted preferences, and theme switching) while keeping application logic separate from JavaFX UI code.

## Requirements

- JDK 21 (includes `jpackage`)
- Gradle Wrapper (`./gradlew`); no separately installed Gradle required
- Desktop environment supported by JavaFX for launching the UI

JavaFX 21.0.13 and JUnit 5.14.4 are declared in `build.gradle.kts`. Gradle selects the JavaFX native dependency for the current OS/architecture.

## Get started

```bash
./gradlew run       # launch
./gradlew test      # tests
./gradlew build     # compile, test, and assemble
./gradlew packageApp # app image under build/jpackage/JavaFXStarter
```

Open the repository root in IntelliJ IDEA and import it as a Gradle project. The FXML files can be edited with Gluon Scene Builder.

## Project map

```text
src/main/java/com/example/app/
  Launcher.java             # classpath-safe main entry point
  MainApp.java              # JavaFX startup and primary stage
  AppContext.java           # shared app services/state
  AppPreferences.java       # persisted user preferences
  Theme.java                # theme choices
  ThemeManager.java         # applies theme styles
  ui/                       # FXML controllers and view navigation
src/main/resources/com/example/app/
  main-view.fxml            # application shell
  home-view.fxml            # home screen
  settings-view.fxml        # settings screen
  styles.css                # shared styles
src/test/java/              # JUnit tests
```

## Architecture notes

This starter intentionally has no `module-info.java`: JavaFX libraries are used on the classpath, and `Launcher` delegates to `MainApp` so JavaFX startup works reliably. FXML controllers are created through a controller factory so shared app context can be injected. Keep business logic independent of JavaFX where practical, and add tests under `src/test`.

`packageApp` builds a platform-specific app image using the JDK's `jpackage`. Run packaging on the target platform; the resulting image is not a universal cross-platform bundle. Installer formats require their platform-specific tooling.

## Troubleshooting

- Confirm `java -version` reports JDK 21 and `JAVA_HOME` points to that JDK.
- On Linux graphics issues, try `JAVA_TOOL_OPTIONS="-Dprism.order=sw" ./gradlew run`.
- JavaFX packaging and native libraries are platform-specific; build and test on each target OS.

See [TODOS.md](TODOS.md) for suggested next steps and project-specific follow-ups.
