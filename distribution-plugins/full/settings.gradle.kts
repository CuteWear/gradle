pluginManagement {
    includeBuild("../../build-logic/build-logic-base")
}

plugins {
    id("gradlebuild.settings-plugins")
}

includeBuild("../native")
includeBuild("../publishing")
