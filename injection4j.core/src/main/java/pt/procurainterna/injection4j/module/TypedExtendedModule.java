package pt.procurainterna.injection4j.module;

import java.util.Optional;
import pt.procurainterna.injection4j.strategy.Strategy;

public class TypedExtendedModule implements Module {

  private final Module baseModule;
  private final Module extensions;

  public TypedExtendedModule(final Module baseModule, final Module extensions) {
    this.baseModule = baseModule;
    this.extensions = extensions;
  }

  @Override
  public <T> Optional<Strategy<T>> strategyForType(final Class<T> type) {
    final Optional<Strategy<T>> extensionStrategy = extensions.strategyForType(type);
    if (extensionStrategy.isPresent()) {
      return extensionStrategy;
    }

    return baseModule.strategyForType(type);
  }

}
