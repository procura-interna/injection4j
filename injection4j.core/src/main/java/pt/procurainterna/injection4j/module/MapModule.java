package pt.procurainterna.injection4j.module;

import java.util.Map;
import java.util.Optional;

import pt.procurainterna.injection4j.strategy.Strategy;

public class MapModule implements Module {

  private final Map<? super Class<?>, ? extends Strategy<?>> map;

  public MapModule(final Map<? super Class<?>, ? extends Strategy<?>> map) {
    this.map = map;
  }

  @Override
  public <T> Optional<Strategy<T>> strategyFor(final Class<T> type) {
    final Strategy<T> strategy = (Strategy<T>) map.get(type);

    return Optional.ofNullable(strategy);
  }

}
