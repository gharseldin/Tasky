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
    includeBuild("build-logic")
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Tasky"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":authentication:presentation")
include(":authentication:data")
include(":authentication:domain")
include(":agenda:presentation")
include(":agenda:data")
include(":agenda:domain")
include(":agenda:network")
include(":core:presentation:ui")
include(":core:presentation:designsystem")
include(":core:data")
include(":core:database")
include(":core:domain")

