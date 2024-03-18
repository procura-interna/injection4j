Reflectionless dependency injection.

Usage example:

```java
package pt.procurainterna.injection4j.example;

import pt.procurainterna.injection4j.module.Module;
import pt.procurainterna.injection4j.module.ModuleBuilder;
import pt.procurainterna.injection4j.module.ModuleBuilders;
import pt.procurainterna.injection4j.provider.Provider;
import pt.procurainterna.injection4j.provider.Providers;

public class SimpleExample {
  
  public void run() {
    ModuleBuilder moduleBuilder = ModuleBuilders.map();
    moduleBuilder.addValue(String.class, "Foo");

    Module module = moduleBuilder.build();    
    Provider provider = Providers.recursive(module);

    String string = provider.provide(String.class);
    System.out.print(string); // Prints "Foo"
  }

}
```
