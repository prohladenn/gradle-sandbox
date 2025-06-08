rootProject.name = "gradle-sandbox"

include(":super-tests")

includeBuild("super-library")
includeBuild("super-service")

