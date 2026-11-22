# Testing

## Existing setup

Tests live under `src/test/java` and use JUnit 5. Gradle's `test` task runs them through the JUnit Platform:

```bash
./gradlew test
```

`GreeterTest` demonstrates testing application logic without starting JavaFX. Prefer this style for domain rules and other deterministic behavior.

## What to test

- Application logic: normal cases, boundary cases, and invalid inputs.
- Preferences: defaults, persistence, and fallback behavior for unknown values.
- Theme behavior: selected theme is saved and applied.
- Navigation: requested views load and controller wiring is correct.

For JavaFX-dependent behavior, isolate the logic behind small methods or services where possible. UI tests may require JavaFX toolkit initialization and a graphical display; keep those distinct from fast headless unit tests.

## Test conventions

Use descriptive test names, arrange/act/assert structure, and avoid coupling tests to implementation details. Tests that touch the real JDK Preferences store should use a dedicated temporary node and clean it up so runs remain isolated. Run `./gradlew test` before submitting changes; use `./gradlew build` to verify the broader build.
