# Architecture

## Overview

The application is a small, non-modular JavaFX desktop app. JavaFX dependencies are placed on the classpath; there is deliberately no `module-info.java`.

```text
Launcher -> MainApp -> main-view.fxml
                         |-- HomeController
                         |-- SettingsController
                         `-- Views / controller factory
AppContext -> AppPreferences, Greeter, theme callback
```

## Startup and view wiring

`Launcher` is the plain Java `main` entry point configured in Gradle. It delegates to `MainApp`, avoiding JavaFX's classpath-launcher issue when the main class itself extends `Application`. `MainApp` creates the shared `AppContext`, loads the FXML shell, installs the controller factory from `Views`, attaches the stylesheet, applies the stored theme, and displays the primary stage.

FXML controllers receive shared dependencies through the controller factory rather than constructing application services themselves. Keep controllers focused on translating user interaction into calls to application logic.

## Shared state and preferences

`AppContext` acts as a small composition root. It currently owns `AppPreferences`, a `Greeter`, and the callback used to apply theme changes to the live scene. `AppPreferences` wraps the JDK Preferences API and stores the theme key. Unknown or missing theme keys fall back to `Theme.LIGHT`.

Keep core logic independent of JavaFX where practical. This makes it easier to unit-test without launching a UI. If the application grows, introduce explicit service interfaces and persistence boundaries rather than turning `AppContext` into a general-purpose service locator.

## Resources

FXML and CSS are under `src/main/resources/com/example/app/`. Load resources through class-relative absolute paths and fail clearly when required resources are missing. Keep shared styling in `styles.css`; use semantic style classes rather than inline style strings where possible.

## Growth guidelines

- Put UI controllers in `com.example.app.ui` and keep them small.
- Put application/domain logic outside controller classes.
- Pass dependencies explicitly and avoid global mutable state.
- Add tests for behavior and persistence as features are introduced.
- Revisit modularization only when there is a concrete need for strict module boundaries or `jlink` runtime images.
