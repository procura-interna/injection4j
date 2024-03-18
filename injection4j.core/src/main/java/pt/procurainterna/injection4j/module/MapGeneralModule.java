package pt.procurainterna.injection4j.module;

import java.util.Map;
import java.util.Optional;
import pt.procurainterna.injection4j.strategy.Strategy;

/**
 * Uses a {@link Map} as the supporting data structure.
 */
public class MapGeneralModule<T> implements GeneralModule<T> {

  private final Map<T, Strategy<?>> map;

  public MapGeneralModule(final Map<T, Strategy<?>> map) {
    this.map = map;
  }

  @Override
  public <T1> Optional<Strategy<T1>> strategyFor(final T key) {
    @SuppressWarnings("unchecked")
    final Strategy<T1> strategy = (Strategy<T1>) map.get(key);

    return Optional.ofNullable(strategy);
  }

}
