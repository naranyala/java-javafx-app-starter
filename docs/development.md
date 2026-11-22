# Development guide

## Requirements

- JDK 21 (needed for compilation and `jpackage`)
- The checked-in Gradle Wrapper
- A desktop environment to launch the JavaFX UI

JavaFX 21.0.13 is resolved from Maven Central. The build selects a native JavaFX classifier based on the host OS and architecture.

## Common commands

Run these from the repository root:

```bash
./gradlew tasks       # list available tasks
./gradlew run         # launch the desktop application
./gradlew test        # run unit tests
./gradlew build       # compile, test, and assemble
./gradlew clean       # remove generated build output
./gradlew packageApp  # create a native app image
```

On Windows, use `gradlew.bat` instead of `./gradlew`.

## IDE workflow

Open the repository root as a Gradle project and select JDK 21 as the project/toolchain JDK. Edit FXML under `src/main/resources/com/example/app/`; Gluon Scene Builder can open these files directly. Keep code formatted consistently with the existing Java style and use Gradle tasks as the authoritative build/test workflow.

## Adding a feature

1. Identify whether the behavior belongs in application logic or a UI controller.
2. Implement reusable/business behavior outside JavaFX controllers when feasible.
3. Add or update the FXML and CSS for presentation changes.
4. Add tests for the behavior and run `./gradlew test`.
5. Run `./gradlew run` to check the complete UI flow on a supported desktop.
