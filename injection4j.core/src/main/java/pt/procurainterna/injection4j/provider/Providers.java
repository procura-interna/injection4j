package pt.procurainterna.injection4j.provider;

import pt.procurainterna.injection4j.module.Module;

public final class Providers {

  private Providers() {
  }

  public static Provider recursive(final Module module) {
    return new RecursiveModuleProvider(module);
  }

}
