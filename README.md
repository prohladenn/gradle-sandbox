# Gradle Sandbox

Example repository combining **Multi‑Project Build** and **Composite Build** with Gradle.

---

## Why this repo exists

This project shows a minimal but fully‑working setup where:

1. The **root repository** remains a classic multi‑project build (module `super-library` is a regular sub‑project).
2. **One module** (`super-service`) is **extracted** into its own build and linked back to the root via `includeBuild(...)`, thus becoming a **composite build**.

**Benefits**

* Develop and build `super-service` in complete isolation, inside its own folder with its own `settings.gradle.kts`.
* When you build the *whole* repo, Gradle substitutes the Maven dependency with the **live** project `:super-library`; no external publication is needed.
* The same pattern scales to large monorepos where services, CLIs, or libraries must also build independently.

---

## Repository layout

```text
gradle-sandbox/
├── build.gradle.kts          root build script
├── settings.gradle.kts       declares multi‑project + includeBuild
│
├── super-library/            regular sub‑project
│   └── build.gradle.kts
│
├── super-tests/              consumer of super-service
│   └── build.gradle.kts
│
└── super-service/            standalone composite build
    ├── settings.gradle.kts
    └── build.gradle.kts
```

### Artifact coordinates

* **group:** `org.example`
* **version:** `1.0-SNAPSHOT`

`super-library` is published to **mavenLocal()** for demonstration purposes.

---

## How to use it

| Scenario                 | Command                                        | What happens                                                                         |
| ------------------------ | ---------------------------------------------- | ------------------------------------------------------------------------------------ |
| Build everything         | `./gradlew build`                              | `super-service` is compiled, pulling the **live** project `:super-library`.          |
| Work only on the service | `cd super-service && ./gradlew build`          | Gradle resolves `org.example:super-library:1.0-SNAPSHOT` from `mavenLocal()`.        |
| Publish the library      | `./gradlew :super-library:publishToMavenLocal` | Generates JAR & POM in `~/.m2/...`; run once before building the service standalone. |

---

## About `super-tests`

The `super-tests` module is a regular sub‑project **inside the main build** that depends on `super-service`.
Because `super-service` is attached via `includeBuild`, `super-tests` can compile and run against the **fresh output** of `super-service` without you having to publish that service to any external repository. This showcases how internal consumers benefit from composite builds while staying in the same repo.

---

## Key files to explore

1. **`settings.gradle.kts` (root)**

    * `include(":super-library")` — standard sub‑project definition.
    * `includeBuild("super-service") { dependencySubstitution { … } }` — the composite magic.
2. **`super-service/build.gradle.kts`**
   Declares its library dependency by coordinates instead of `project(...)`:

   ```kotlin
   implementation("org.example:super-library:1.0-SNAPSHOT")
   ```
3. **`super-library/build.gradle.kts`**
   Applies `maven-publish` so the module can be installed to the local Maven cache.

---

## Requirements

* **JDK 17+**
* **Gradle 8.0+** (update wrapper if necessary)
