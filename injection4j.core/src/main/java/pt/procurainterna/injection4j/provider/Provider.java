package pt.procurainterna.injection4j.provider;

/**
 * Obtain instances of the desired types.
 */
public interface Provider {

  <T> T provide(Class<T> type);

}
