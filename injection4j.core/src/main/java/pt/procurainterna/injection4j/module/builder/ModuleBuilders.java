package pt.procurainterna.injection4j.module.builder;

public final class ModuleBuilders {

  private ModuleBuilders() {
  }

  public static ModuleBuilder map() {
    return new MapModuleBuilder();
  }

}
