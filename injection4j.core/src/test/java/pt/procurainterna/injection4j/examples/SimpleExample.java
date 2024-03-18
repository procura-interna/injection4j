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
