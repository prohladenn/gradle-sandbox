# Gradle Sandbox

Example repository combining **Multi‑Project Build** and **Composite Build** with Gradle.

---

## Why this repo exists

This project shows a minimal but fully‑working setup where:

1. Both **`super-library`** and **`super-service`** live as standalone builds.
2. The root build links them back via `includeBuild(...)` while `super-tests` remains a regular sub‑project.

**Benefits**

* Develop and build `super-service` in complete isolation, inside its own folder with its own `settings.gradle.kts`.
* When you build the *whole* repo, Gradle substitutes those Maven coordinates with the **live** projects; no external
  publication is needed.
* The same pattern scales to large monorepos where services, CLIs, or libraries must also build independently.

---

## Repository layout

```text
gradle-sandbox/
├── build.gradle.kts          root build script
├── settings.gradle.kts       declares multi‑project + includeBuild
│
├── super-library/            standalone composite build
│   ├── settings.gradle.kts
│   └── build.gradle.kts
│
├── super-tests/              regular sub‑project consuming the service
│   └── build.gradle.kts
│
└── super-service/            standalone composite build
    ├── settings.gradle.kts
    └── build.gradle.kts
```

### Artifact coordinates

* **group:** `org.example`

Everything is built locally via composite builds; no publication is required.

---

## How to use it

| Scenario               | Command                          | What happens                                                       |
|------------------------|----------------------------------|--------------------------------------------------------------------|
| Build everything       | `./gradlew build`                | Both modules are compiled directly with their sources substituted. |
| Build only the service | `./gradlew :super-service:build` | The library is pulled in as a live project via `includeBuild`.     |

---

## About `super-tests`

The `super-tests` module is a regular sub‑project **inside the main build** that depends on `super-service`.
Because `super-service` is attached via `includeBuild`, `super-tests` can compile and run against the **fresh output**
of `super-service` without you having to publish that service to any external repository. This showcases how internal
consumers benefit from composite builds while staying in the same repo.

---

## Key files to explore

1. **`settings.gradle.kts` (root)**

    * `includeBuild("super-library")`
    * `includeBuild("super-service")`
2. **`super-service/build.gradle.kts`**
   Declares its library dependency by coordinates instead of `project(...)`:

   ```kotlin
   implementation("org.example:super-library:1.0-SNAPSHOT")
   ```
3. **`super-library/build.gradle.kts`**
   Applies `maven-publish` in case you want to install the library to your local Maven cache.

---

## Requirements

* **JDK 17+**
* **Gradle 8.0+** (update wrapper if necessary)
