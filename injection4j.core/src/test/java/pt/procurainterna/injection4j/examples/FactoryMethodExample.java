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
