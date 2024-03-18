package pt.procurainterna.injection4j.module;

import java.util.Optional;
import pt.procurainterna.injection4j.strategy.Strategy;

public class ExtendedGeneralModule<K> implements GeneralModule<K> {

  private final GeneralModule<? super K> baseModule;
  private final GeneralModule<? super K> extensions;

  public ExtendedGeneralModule(final GeneralModule<? super K> baseModule,
      final GeneralModule<? super K> extensions) {
    this.baseModule = baseModule;
    this.extensions = extensions;
  }

  @Override
  public <T> Optional<Strategy<T>> strategyFor(final K key) {
    final Optional<Strategy<T>> extensionStrategy = extensions.strategyFor(key);
    if (extensionStrategy.isPresent()) {
      return extensionStrategy;
    }

    return baseModule.strategyFor(key);
  }

}
