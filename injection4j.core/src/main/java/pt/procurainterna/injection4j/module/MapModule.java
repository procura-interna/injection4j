package pt.procurainterna.injection4j.module;

import java.util.Map;
import java.util.Optional;
import pt.procurainterna.injection4j.strategy.Strategy;

/**
 * Uses a {@link Map} as the supporting data structure.
 */
public class MapModule implements Module {

  private final Map<Class<?>, Strategy<?>> map;

  public MapModule(final Map<Class<?>, Strategy<?>> map) {
    this.map = map;
  }

  @Override
  public <T> Optional<Strategy<T>> strategyFor(final Class<T> type) {
    @SuppressWarnings("unchecked")
    final Strategy<T> strategy = (Strategy<T>) map.get(type);

    return Optional.ofNullable(strategy);
  }

}
