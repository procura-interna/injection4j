package pt.procurainterna.injection4j.strategy;

import pt.procurainterna.injection4j.fetcher.Fetcher;

public interface Strategy<T> {

  T execute(Fetcher fetcher);

}
