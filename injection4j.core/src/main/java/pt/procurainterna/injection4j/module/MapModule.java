package pt.procurainterna.injection4j.module;

import java.util.HashMap;
import java.util.Optional;
import pt.procurainterna.injection4j.strategy.Strategy;

public class MapModule implements Module {

  private final HashMap<Class, Strategy> map;

  public MapModule(final HashMap<Class, Strategy> map) {
    this.map = map;
  }

  @Override
  public <T> Optional<Strategy<T>> strategyFor(final Class<T> type) {
    final Strategy<T> strategy = map.get(type);

    return Optional.ofNullable(strategy);
  }

}
