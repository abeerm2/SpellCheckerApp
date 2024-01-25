# SpellCheckerApp

## Prerequisites

Before you can run the SpellCheckerApp, you need to have the following installed on your system:

- Java Development Kit (JDK) 11 or later
- JavaFX SDK
- JUnit 5
- TestFX

## Running the Application

1. **Navigate to the project directory**

   Open a terminal and navigate to the directory where the SpellCheckerApp project is located.

2. **Set the PATH_TO_FX environment variable**

   This variable should point to the `lib` directory of the JavaFX SDK installation on your system. Replace `/path/to/javafx-sdk-11.0.2/lib` with the actual path on your system.

    ```bash
    export PATH_TO_FX=/path/to/javafx-sdk-11.0.2/lib
    ```

3. **Compile the application**

   Navigate to the `src/main/java` directory and compile the `MainApplication.java` file. Don't forget to include the path to the JavaFX SDK.

    ```bash
    cd src/main/java
    javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml SpellCheckerApp/MainApplication.java
    ```

4. **Run the application**

   Still in the `src/main/java` directory, you can now run the application:

    ```bash
    java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml SpellCheckerApp.MainApplication
    ```

## Running the Tests

The tests for the SpellCheckerApp are located in the `src/test/java` directory. To run the tests, you'll need to have JUnit 5 and TestFX installed.

1. **Navigate to the test directory**

   Open a terminal and navigate to the `src/test/java` directory in the SpellCheckerApp project.

2. **Compile the tests**

   Compile all the test files in the `SpellCheckerApp` directory.

    ```bash
    cd SpellCheckerApp
    javac *.java
    ```

3. **Run the tests**

   Use the JUnit 5 Console Launcher to run the tests. Replace `/path/to/junit-platform-console-standalone-1.8.1.jar` with the actual path to the JUnit 5 Console Launcher on your system.

    ```bash
    java -jar /path/to/junit-platform-console-standalone-1.8.1.jar --class-path . --scan-class-path
    ```
