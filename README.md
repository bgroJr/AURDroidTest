# :iphone: :white_check_mark: AURDroidTest

Example UI tests for the [AURDroid](https://f-droid.org/en/packages/com.rascarlo.aurdroid/) mobile application
for [Android](https://developer.android.com).

Automated UI tests are written in [Kotlin](https://kotlinlang.org) (script), with [Appium](http://appium.io)
for mobile app interaction. 

## Purpose

Proof of concept for writing automated test scripts with the experimental
[scripting mode](https://kotlinlang.org/docs/tutorials/command-line.html#using-the-command-line-to-run-scripts)
of the Kotlin runtime. Attempting to show the viability of a lighter weight workflow, and avoid
[Java](https://www.oracle.com/java) :stuck_out_tongue_closed_eyes: 

## Setup

```
# on Mac OS, use HomeBrew and NPM to install system dependencies
# ... and let's just assume you have the Android SDK installed already
$ brew install kotlin
$ brew install node
$ npm install -g appium@latest
```

## Run

```
$ kotlinc -script TestRunner.main.kts
```

## Tests

The tests are defined in **AURDroid.kts**. The tests interact with the AURDroid mobile application. 

Each screen of the mobile application is represented by a class which implements methods of 
interacting with and retrieving information from the screen: **Home.kts**, **Results.kts**, **Package.kts** 
These classes are the mobile version of the [Page Object Model](https://martinfowler.com/bliki/PageObject.html) 
design pattern. 

**TestRunner.main.kts** contains a minimal test runner using 
[JUnitCore](https://junit.org/junit4/javadoc/latest/org/junit/runner/JUnitCore.html).

## .main.kts

When you give a Kotlin file the _.main.kts_ extension, Kotlin's experimental scripting extension 
will trigger for script execution. The experimental extension enables special annotations like 
_@file:DependsOn_ to specify external project dependencies, and _@file:Import_ to specify internal 
dependencies. 

Putting these annotations at the top of a script file saves you some work and complexity that 
you're normally stuck with when you work with a [JVM-based language](https://en.wikipedia.org/wiki/List_of_JVM_languages). 
And I'm getting pretty tired of editing [Gradle](https://docs.gradle.org/current/userguide/kotlin_dsl.html) 
build scripts :elephant: :weary:

