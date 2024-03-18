package pt.procurainterna.injection4j.provider;

class InClassContextProvider<T> implements Provider {

  private final Provider provider;
  private final Class<T> type;

  InClassContextProvider(final Provider provider, final Class<T> type) {
    this.provider = provider;
    this.type = type;
  }

  @Override
  public <D> D provide(final Class<D> dependencyType) {
    try {
      return provider.provide(dependencyType);

    } catch (final NoStrategyFoundException e) {
      throw new UnresolvedDependencyException(type, dependencyType, e);
    }
  }

}
