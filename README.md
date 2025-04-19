# Android Modular App Architecture with Kotlin and Jetpack Compose

Scalable Android application architecture using Kotlin and Jetpack Compose with modularization across multiple repositories.

## Project Structure Overview
The architecture will consist of:
- Host App: Main application container that aggregates feature modules
- Feature Modules: Separate repositories of independent modules for Dashboard, Services, and Profile
- Version Catalog: Centralized dependency management via libs.versions.toml


## Key Design Practices
### 1. Modularization Strategy

Feature Modules: Each tab (Dashboard, Services, Profile) is a separate module in its own repository
Clean API Boundaries: Feature modules expose only what's needed via dedicated API packages
Dependency Management: Modules are consumed as external libraries via Maven publishing

### 2. Clean Architecture in Modules (continued)
Each feature module follows Clean Architecture principles:

Presentation Layer: Compose UI components and ViewModels
Domain Layer: Business logic with use cases, repository interfaces, and domain models
Data Layer: Repository implementations and data sources (remote and local)

### 3. Dependency Injection

Hilt: Used for dependency injection throughout the application
Module-specific DI: Each feature module provides its own components
Scoped Providers: Appropriate scoping of dependencies (Singleton vs Activity)

### 4. Centralized Dependency Management

Version Catalog: Using libs.versions.toml to manage all dependency versions
Dependency Bundles: Creating logical groupings of dependencies
Single Source of Truth: All modules reference the same version catalog

### 5. Navigation

Compose Navigation: Bottom navigation with NavHost for seamless transitions
Decoupled Navigation: Features don't know about each other's implementation details
Deep Linking Support: Structure supports deep linking to specific screens

### 6. Repository Pattern

Repository Interface: Defined in the domain layer
Repository Implementation: In the data layer
Offline-First: Cache data locally and handle network failures gracefully

### 7. Multi-Repository Development

Maven Publishing: Each feature module is published as a Maven artifact
Version Management: Clear versioning strategy for modules
Team Collaboration: Enables parallel development by different teams

### 8. Testing Strategy

Unit Testing: For business logic and ViewModels
UI Testing: For Compose components
Module Testing: Each module can be tested in isolation

## How to Implement Feature Modules for Services and Profile
For the Services and Profile feature modules, you would follow the same pattern as the Dashboard module:

- Create separate repositories for each feature
- Set up the build.gradle.kts with Maven publishing
- Implement the feature following Clean Architecture
- Expose only what's needed via a public API package

## Next Steps for Implementation

CI/CD Setup: Configure CI/CD pipelines for each repository
Code Quality Tools: Add static analysis tools like Detekt and Ktlint
Versioning Strategy: Create a strategy for versioning modules
Documentation: Create architectural documentation for team reference

## Key Benefits of This Architecture

- Team Scaling: Different teams can work on separate modules
- Build Time Reduction: Smaller modules lead to faster builds
- Codebase Maintainability: Clear separation of concerns
- Reusability: Feature modules can be reused across apps
- Testing in Isolation: Each module can be tested independently
- This architecture provides a solid foundation for building a large-scale Android application using modern practices while enabling effective collaboration between teams working on different features.


## Packaging a Feature Module as a Library
To package the Dashboard feature as a library and import it into your main app, you'll need to:

- Set up the Dashboard module as a publishable library
- Publish it to a repository (local, Maven, etc.)
- Configure the main app to consume it

## Publish the Library
```
./gradlew clean assembleRelease

# If you named your repository "localRepo" (camelCase)
./gradlew publishReleasePublicationToLocalRepoRepository

# If you didn't explicitly name your repository
./gradlew publishReleasePublicationToMavenLocal
```

 ## Implement Composite Builds (for Development)
 In your main app's settings.gradle.kts:
```
 includeBuild("../feature-dashboard") {
    dependencySubstitution {
        substitute(module("com.company.feature:dashboard"))
            .using(project(":"))
        substitute(module("com.company.feature:dashboard-api"))
            .using(project(":api"))
    }
}
```
