@file:DependsOn("junit:junit:4.13")
@file:DependsOn("io.appium:java-client:7.3.0")

@file:Import("AURDroid.kts")
@file:Import("Home.kts")
@file:Import("Results.kts")
@file:Import("Package.kts")

import org.junit.runner.JUnitCore

val results = JUnitCore.runClasses(AURDroid.UITest::class.java)

println(
  "Ran ${results.getRunCount()} tests " +
  "in ${results.getRunTime() / 1000.0} seconds " +
  "with ${results.getFailureCount()} failures."
)

if (results.getFailureCount() > 0) {
  results.getFailures().forEach {
    println(it.getMessage())
    println(it.getException())
  }
}

