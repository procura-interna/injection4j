# Injection4j

Validation-free, reflection-light dependency injection library for Java.

## Overview

Injection4j is a lightweight dependency injection container designed for simplicity and performance. Unlike traditional DI frameworks that rely heavily on runtime reflection and classpath scanning (like Spring or Guice), Injection4j focuses on explicit configuration using Java 8+ functional interfaces.

**Key Features:**
*   **Zero Configuration Magic:** No `@Inject` annotation scanning or XML files.
*   **Type-Safe:** Uses `Class<T>` keys and lambda-based factories.
*   **Reflection-Light:** mostly avoids `java.lang.reflect` for instantiation, giving control back to the developer via `Supplier` and functional interfaces.
*   **Fast Startup:** Ideal for environments where startup time matters.

## Installation

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>pt.procurainterna</groupId>
    <artifactId>injection4j.core</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

### 1. Define your components

```java
public class MyService {
    private final MyRepository repository;
    
    public MyService(MyRepository repository) {
        this.repository = repository;
    }
}
```

### 2. Build a Module

Use `MapModuleBuilder` to wire your dependencies explicitely:

```java
import pt.procurainterna.injection4j.module.MapModuleBuilder;
import pt.procurainterna.injection4j.module.Module;

Module module = new MapModuleBuilder()
    .addValue(MyRepository.class, new MyRepository()) // Singleton value
    .addInvocation(MyService.class, MyService::new, MyRepository.class) // Constructor injection
    .build();
```

### 3. Retrieve Dependencies

Use `RecursiveModuleProvider` to resolve the graph:

```java
import pt.procurainterna.injection4j.provider.RecursiveModuleProvider;

RecursiveModuleProvider provider = new RecursiveModuleProvider(module);
MyService service = provider.provide(MyService.class);
```

## License

This project is released under the [Unlicense](https://unlicense.org/).
