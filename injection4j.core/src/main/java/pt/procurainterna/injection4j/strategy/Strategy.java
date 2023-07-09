package pt.procurainterna.injection4j.strategy;

import pt.procurainterna.injection4j.provider.Provider;

/**
 * Return an instance of a desired type, using a dependency injection aid if necessary.
 *
 * @param <T> The type of the instance returned.
 */
public interface Strategy<T> {

  T execute(Provider provider);

}
