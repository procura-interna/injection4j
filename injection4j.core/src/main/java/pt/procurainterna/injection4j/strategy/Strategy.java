package pt.procurainterna.injection4j.strategy;

import pt.procurainterna.injection4j.provider.Provider;

public interface Strategy<T> {

  T execute(Provider provider);

}
