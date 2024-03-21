package pt.procurainterna.injection4j.provider;

import pt.procurainterna.injection4j.module.Module;

public final class Providers {

  private Providers() {
  }

  public static Provider recursive(final Module module) {
    return new RecursiveModuleProvider(module);
  }

  /**
   * Used for combining providers. When an instance of a type is being obtained, {@code extension}
   * will be used first. If none is found, {@code base} is used.
   */
  public static Provider extended(final Provider base, final Provider extension) {
    return new ExtendedProvider(base, extension);
  }

}
