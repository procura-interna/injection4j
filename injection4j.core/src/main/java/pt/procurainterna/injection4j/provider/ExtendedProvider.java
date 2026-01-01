package pt.procurainterna.injection4j.provider;

public class ExtendedProvider implements Provider {

  private final Provider provider;
  private final Provider extension;

  public ExtendedProvider(final Provider provider, final Provider extension) {
    this.provider = provider;
    this.extension = extension;
  }

  @Override
  public <T> T provide(final Class<T> type) {
    try {
       return extension.provide(type);

    } catch (final NoStrategyFoundException e) {
      return provider.provide(type);
    }
  }

}
