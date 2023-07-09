package pt.procurainterna.injection4j.provider;

import pt.procurainterna.injection4j.fetcher.Fetcher;

class ProviderClassFetcher<T> implements Fetcher {

  private final Provider provider;
  private final Class<T> type;

  ProviderClassFetcher(final Provider provider, final Class<T> type) {
    this.provider = provider;
    this.type = type;
  }

  @Override
  public <D> D fetch(final Class<D> dependencyType) {
    try {
      return provider.provide(dependencyType);

    } catch (final NoStrategyFoundException e) {
      throw new UnresolvedDependencyException(type, dependencyType, e);
    }
  }

}
