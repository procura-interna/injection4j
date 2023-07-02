Reflectionless dependency injection.

Usage example:

````java
package pt.procurainterna.injection4j.example;

import pt.procurainterna.injection4j.module.MapModuleBuilder;
import pt.procurainterna.injection4j.module.Module;
import pt.procurainterna.injection4j.module.ModuleBuilder;
import pt.procurainterna.injection4j.provider.Provider;
import pt.procurainterna.injection4j.provider.RecursiveModuleProvider;
import pt.procurainterna.injection4j.provider.UnresolvedDependencyException;

public class Example {
  
  public void run() {
    final ModuleBuilder moduleBuilder = new MapModuleBuilder();
    
    // Configure the dependencies
    moduleBuilder.addValue(String.class, "Foo");
    moduleBuilder.addValue(Integer.class, 42);
    
    // Configure the constructor call to use
    moduleBuilder.addInvocation(ClassWithDependencies.class, ClassWithDependencies::new, String.class, Integer.class);

    // Obtain a modular representation of the configuration. Can be combined with other modules.
    final Module module = moduleBuilder.build();
    
    // This provider uses the module to perform dependency injection
    final Provider provider = new RecursiveModuleProvider(module);

    // Use the provider to obtain an instance of the desired class
    final ClassWithDependencies provided = provider.provide(ClassWithDependencies.class);
    
    final String string = provided.string; // is "Foo", as configured
    final Integer integer = provider.integer; // is 42, as configured
  }

  public static class ClassWithDependencies {
    
    public String string;
    public Integer integer;
    
    public ClassWithDependencies(String string, Integer integer) {
      this.string = string;
      this.integer = integer;
    }
  }
}
````