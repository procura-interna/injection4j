package pt.procurainterna.injection4j.module;

public final class Modules {

  private Modules() {
  }

  /**
   * Used for combining modules. When an instance of a type is being obtained, {@code extension}
   * will be used first. If none is found, {@code base} is used.
   */
  public static Module extended(final Module base, final Module extension) {
    return new ExtendedModule(base, extension);
  }

}
