pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()

        // Add the local repo (for development)
        maven { url = uri("/Users/czy/workspace/android/modular-app/libs") }

        // In production environment, you would use your organization's Maven repository
        // For a remote repository:
        // maven {
        //     url = uri("https://your-remote-repo.com/repository")
        //     credentials {
        //         username = findProperty("REPO_USERNAME") as String?
        //         password = findProperty("REPO_PASSWORD") as String?
        //     }
        // }
    }

}

// Para entorno de desarrollo local
//includeBuild("../profile") {
//    dependencySubstitution {
//        substitute(module("com.example.modules:profile"))
//            .using(project(":profile:api"))
//    }
//}

rootProject.name = "Main App"
include(":app")