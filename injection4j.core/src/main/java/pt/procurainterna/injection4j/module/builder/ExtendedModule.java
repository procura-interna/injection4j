package pt.procurainterna.injection4j.module.builder;

import java.util.Optional;
import pt.procurainterna.injection4j.module.Module;
import pt.procurainterna.injection4j.strategy.Strategy;

public class ExtendedModule implements Module {

  private final Module module;
  private final Module extension;

  public ExtendedModule(final Module module, final Module extension) {
    this.module = module;
    this.extension = extension;
  }

  @Override
  public <T> Optional<Strategy<T>> strategyFor(final Class<T> type) {
    final Optional<Strategy<T>> strategyFromExtension = strategyFromExtension(type);

    if (strategyFromExtension.isPresent()) {
      return strategyFromExtension;
    }

    return strategyFromBase(type);
  }

  private <T> Optional<Strategy<T>> strategyFromExtension(final Class<T> type) {
    return extension.strategyFor(type);
  }

  private <T> Optional<Strategy<T>> strategyFromBase(final Class<T> type) {
    return module.strategyFor(type);
  }

}
