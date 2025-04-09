# Project Structure

The project is organised around Kotlin Multiplatform and follows the principles of Clean Architecture combined with an MVI approach.

## Distribution of sources

- AndroidMain**: Android-specific code (implementations, Android resources).
- desktopMain**: Desktop-specific code.
- commonMain**: Code shared between all platforms.
  - data**: Manages data access (API via Ktor, cache, Room, DataStore, etc.).
  - domain**: Contains the business entities and repository interfaces.
  - ui**: User interface with Jetpack Compose, ViewModels and navigation, using the MVI pattern.

## MVI approach

The MVI flow is structured as follows:
- The user triggers an **action**.
- The **ViewModel** captures the action and updates the **state**.
- The **view** observes the state and updates itself accordingly.

This provides a unidirectional data flow, making it easier to manage and test the interface.

