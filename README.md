# Dynamic Java Code Executor

This project demonstrates how to dynamically compile and execute Java code provided as a String, with the code source being a JSON file. It is useful for scenarios where you need to run user-provided or external Java code at runtime in a controlled environment.

## Features
- Reads Java code and class name from a JSON file
- Compiles Java code at runtime
- Executes a specified method using reflection
- Cleans up generated files after execution
- Modular design for easy extension

## How It Works
1. The application reads a JSON file (default: `data.json`) containing the Java class name and code.
2. It compiles the code using an in-memory Java compiler.
3. The compiled class is loaded, and a specified method (e.g., `processValue`) is invoked with arguments.
4. The result is printed to the console.
5. Temporary files are cleaned up after execution.

## Usage Instructions

### Prerequisites
- Java 17 or higher
- Maven (for building the project)

### Build
```sh
mvn clean package
```

### Run
```sh
java -cp target/dynamic-java-code-executor-1.0-SNAPSHOT.jar br.com.dynamic.code.ExecutorJsonApplication
```

## Example

**data.json** (in `src/main/resources/`):
```json
{
  "className": "ExampleProcessor",
  "code": "public class ExampleProcessor { public String processValue(String input) { return input.toUpperCase(); } }"
}
```

**Expected Output:**
```
Execution result: EXEMPLO
```

## Project Structure
- `ExecutorJsonApplication.java`: Main entry point
- `sources/impl/JsonCodeSource.java`: Reads code and class name from JSON
- `compilers/impl/JavaStringCompiler.java`: Compiles Java code from String
- `executors/ReflectionClassExecutor.java`: Executes methods via reflection
- `strategies/impl/FileCleanupStrategy.java`: Cleans up generated files

## License
[Specify your license here]
