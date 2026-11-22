# Packaging and releases

## Build an app image

The Gradle `packageApp` task depends on the application distribution and invokes the `jpackage` bundled with the active JDK:

```bash
./gradlew packageApp
```

The current output is `build/jpackage/JavaFXStarter`. It contains a runnable application image and a private runtime. Build on the target operating system and architecture: JavaFX native libraries and `jpackage` output are platform-specific.

## Before distributing

- Replace the sample app name and configure a project-specific version.
- Add platform-appropriate application icons and metadata.
- Test the packaged image on a clean machine, not only through Gradle's `run` task.
- Decide how to sign/notarize binaries and publish updates.
- Keep credentials and signing keys out of the repository.

## Installer formats

The current task creates an `app-image`, not an installer. `jpackage` supports platform-specific installer formats, but each format may require additional tools (for example, platform package builders) and should be configured and tested separately. Consult the JDK `jpackage` documentation for options supported by the release JDK and target OS.

## Release verification

From a clean checkout, run tests and build the artifact on each supported platform. Record the JDK version, OS/architecture, artifact name, and checksums with each release. Do not assume a package built on one operating system can be used on another.
