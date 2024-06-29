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
include(":events:presentation")
include(":events:data")
include(":events:domain")
include(":tasks:presentation")
include(":tasks:data")
include(":tasks:domain")
include(":core:presentation:ui")
include(":core:presentation:designsystem")
include(":core:data")
include(":core:database")
include(":core:network")
include(":core:domain")
