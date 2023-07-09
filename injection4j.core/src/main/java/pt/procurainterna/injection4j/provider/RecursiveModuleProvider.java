package pt.procurainterna.injection4j.provider;

import java.util.Optional;
import pt.procurainterna.injection4j.module.Module;
import pt.procurainterna.injection4j.strategy.Strategy;

public class RecursiveModuleProvider implements Provider {

  private final Module module;

  public RecursiveModuleProvider(final Module module) {
    this.module = module;
  }

  @Override
  public <T> T provide(final Class<T> type) {
    final Optional<Strategy<T>> searchResult = module.strategyFor(type);

    if (searchResult.isEmpty()) {
      throw new NoStrategyFoundException(type);
    }

    final Strategy<T> strategy = searchResult.get();

    return strategy.execute(new ProviderClassFetcher<>(this, type));
  }

}
