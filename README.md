# JavaFX Starter

A minimal, modern JavaFX desktop application scaffold: Java 21, Gradle (Kotlin
DSL), FXML + CSS, JUnit 5, and native packaging via `jpackage`.

## Toolchain

| Tool | Version | Location |
| --- | --- | --- |
| JDK (Temurin) | 21 LTS | `~/.local/opt/jdk-21` |
| Gradle | 9.3.1 (wrapper) | `./gradlew` |
| JavaFX | 21.0.13 | Maven Central (pulled by Gradle) |
| JUnit | 5.14.4 | Maven Central |
| IntelliJ IDEA Community | latest | Flatpak |
| Gluon Scene Builder | latest | Flatpak |

`JAVA_HOME` and `PATH` are configured in `~/.bashrc`, `~/.profile`, and
`~/.config/environment.d/10-java.conf`. Open a new terminal (or log out/in) for
GUI apps to pick them up.

## Global commands

These work from any directory (symlinked into `~/.local/bin`, which is on
`PATH` for both shells and the systemd user session):

| Command | Tool |
| --- | --- |
| `java`, `javac`, `jar`, `jshell` | JDK 21 |
| `jpackage`, `jlink` | JDK packaging tools |
| `keytool`, `jcmd`, `jconsole`, `jstack`, … | JDK diagnostics |
| `gradle` | Gradle 9.3.1 |
| `idea` | IntelliJ IDEA Community (Flatpak) |
| `scene-builder` | Gluon Scene Builder (Flatpak) |

Verify with:

```bash
java -version && javac -version && gradle --version
```

## Quick start

```bash
./gradlew run          # launch the app
./gradlew test         # run unit tests
./gradlew build        # compile + test + assemble
./gradlew packageApp   # native app image in build/jpackage/JavaFXStarter
```

## Project layout

```
src/main/java/com/example/app/
  Launcher.java        # plain main() -> MainApp (classpath-safe JavaFX entry)
  MainApp.java         # Application: loads FXML + CSS
  MainController.java  # @FXML controller
  Greeter.java         # GUI-free logic (unit tested)
src/main/resources/com/example/app/
  main-view.fxml       # UI layout (open in Scene Builder)
  styles.css           # theme
src/test/java/com/example/app/
  GreeterTest.java     # JUnit 5 tests
```

## IntelliJ IDEA

1. **File → Open** and select this folder (the `build.gradle.kts` file).
2. Let Gradle sync. If asked for a JDK, choose `~/.local/opt/jdk-21`
   (or *Add JDK…* → that path).
3. Run the `application` run configuration, or the `run` Gradle task.

### Scene Builder

Open `src/main/resources/com/example/app/main-view.fxml` with Scene Builder:

- **IntelliJ:** *Settings → Languages & Frameworks → JavaFX* → set
  *Scene Builder path* to `flatpak run com.gluonhq.SceneBuilder`
  (or the `scene-builder` executable). Then right-click the FXML →
  *Open in Scene Builder*.

## How the non-modular setup works

The project deliberately has **no `module-info.java`**. JavaFX jars are on the
normal classpath, and `Launcher` is a plain class whose `main` delegates to
`MainApp`. This avoids the classic *"JavaFX runtime components are missing"*
error and keeps FXML reflection working without `opens` declarations.

If you later want a fully modular app (for strict `jlink` images), add a
`module-info.java` and switch `application { mainModule = ... }`.

## Packaging notes

`packageApp` uses the JDK's bundled `jpackage` to produce a self-contained
app image. To build native installers instead:

```bash
jpackage --type rpm ...   # requires rpm-build
jpackage --type deb ...   # requires dpkg/fakeroot
```

Change `--type app-image` to `rpm`, `deb`, etc. in `build.gradle.kts`.

## Troubleshooting

- **`JAVA_HOME` not set in a new shell** — source `~/.bashrc` or open a new
  terminal.
- **Blank/black window on Linux** — try
  `JAVA_TOOL_OPTIONS="-Dprism.order=sw" ./gradlew run`.
- **Wayland issues** — JavaFX 21 runs through XWayland; if a feature misbehaves,
  start with `GDK_BACKEND=x11`.
- **IntelliJ can't find JavaFX** — make sure the Gradle sync completed and the
  project JDK is 21.
