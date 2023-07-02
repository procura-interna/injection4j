package pt.procurainterna.injection4j.provider;

public interface Provider {

  <T> T provide(Class<T> type);

}
