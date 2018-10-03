import org.gradle.gradlebuild.unittestandcompile.ModuleType
import org.gradle.testing.PerformanceTest

/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    id("gradlebuild.classycle")
}

apply(from = "templates.gradle.kts")

dependencies {
    // so that all Gradle features are available
    val allTestRuntimeDependencies: DependencySet by rootProject.extra
    allTestRuntimeDependencies.forEach {
        performanceTestRuntime(it)
    }

    testFixturesApi(project(":internalPerformanceTesting"))
}

gradlebuildJava {
    moduleType = ModuleType.INTERNAL
}

tasks.withType<PerformanceTest>().configureEach {
    systemProperties.put("incomingArtifactDir", "${rootDir}/incoming/")
}
