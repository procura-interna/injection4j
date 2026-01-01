package pt.procurainterna.injection4j.fetcher;

public interface Fetcher {

  <T> T fetch(Class<T> type);

}
