rootProject.name = "gradle-sandbox"

include(":super-library")
includeBuild("super-service")
include(":super-tests")
