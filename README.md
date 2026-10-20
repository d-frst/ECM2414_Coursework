# ECM2414

## Prerequisites
- Java Development Kit (JDK): JDK 17 (tested on Temurin 17.0.20.1)
- JUnit Framework: JUnit 4.13.2
- Hamcrest Core: 1.3
- Dependencies: Contained in the `lib/` directory (`junit-4.13.2.jar`, `hamcrest-core-1.3.jar`)

## Running the Executable Game (`cards.jar`)
To run the production card game application:

java -jar cards.jar

## Compiling the Production and Test Code

Windows (Command Prompt):

## Compile production code:
javac -d bin/main src/main/java/*.java

## Compile test code:
javac -cp "lib/*;bin/main" -d bin/tests src/tests/java/*.java

## To run the test suite:

java -cp "bin/main;bin/tests;lib/*" org.junit.runner.JUnitCore tests.java.CardGameTestSuite