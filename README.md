Reflectionless dependency injection.

Simple usage example:

```java
package pt.procurainterna.injection4j.examples;

import pt.procurainterna.injection4j.module.Module;
import pt.procurainterna.injection4j.module.builder.ModuleBuilder;
import pt.procurainterna.injection4j.module.builder.ModuleBuilders;
import pt.procurainterna.injection4j.provider.Provider;
import pt.procurainterna.injection4j.provider.Providers;

public class SimpleExample {

  public static void main(String[] args) {
    ModuleBuilder moduleBuilder = ModuleBuilders.map();
    moduleBuilder.addValue(String.class, "Foo");

    Module module = moduleBuilder.build();
    Provider provider = Providers.recursive(module);

    String string = provider.provide(String.class);
    System.out.println(string); // Prints "Foo"
  }
}
```

Factory method example with no arguments:

```java
package pt.procurainterna.injection4j.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import pt.procurainterna.injection4j.module.Module;
import pt.procurainterna.injection4j.module.builder.ModuleBuilder;
import pt.procurainterna.injection4j.module.builder.ModuleBuilders;
import pt.procurainterna.injection4j.provider.Provider;
import pt.procurainterna.injection4j.provider.Providers;

public class FactoryMethodExample {

  public static void main(String[] args) {
    ModuleBuilder moduleBuilder = ModuleBuilders.map();
    moduleBuilder.addInvocation(ExecutorService.class, Executors::newSingleThreadExecutor);

    Module module = moduleBuilder.build();
    Provider provider = Providers.recursive(module);

    ExecutorService executor = provider.provide(ExecutorService.class);
    System.out.println("Single thread Executor: " + executor);
    executor.shutdown();
  }
}
```

Factory method with one argument:
```java
package pt.procurainterna.injection4j.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import pt.procurainterna.injection4j.module.Module;
import pt.procurainterna.injection4j.module.builder.ModuleBuilder;
import pt.procurainterna.injection4j.module.builder.ModuleBuilders;
import pt.procurainterna.injection4j.provider.Provider;
import pt.procurainterna.injection4j.provider.Providers;

public class FactoryMethodWithArgumentExample {

  public static void main(String[] args) {
    ModuleBuilder moduleBuilder = ModuleBuilders.map();
    moduleBuilder.addValue(Integer.class, 2);
    moduleBuilder.addInvocation(ExecutorService.class, Executors::newWorkStealingPool,
        Integer.class);

    Module module = moduleBuilder.build();
    Provider provider = Providers.recursive(module);

    ExecutorService executor = provider.provide(ExecutorService.class);
    System.out.println("Executor with 2 threads at most: " + executor);
    executor.shutdown();
  }
}
```
