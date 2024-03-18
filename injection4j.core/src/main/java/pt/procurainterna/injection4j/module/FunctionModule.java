package pt.procurainterna.injection4j.module;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import pt.procurainterna.injection4j.strategy.Strategy;

public class FunctionModule<K> implements GeneralModule<K> {

  private final Function<? super K, ? extends Strategy<?>> function;

  public FunctionModule(final Function<? super K, ? extends Strategy<?>> function) {
    this.function = function;
  }

  public FunctionModule(final Map<K, Strategy<?>> map) {
    this(map::get);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Override
  public <T> Optional<Strategy<T>> strategyFor(final K key) {
    final Strategy rawTypeStrategy = function.apply(key);
    if (rawTypeStrategy == null) {
      return Optional.empty();
    }

    final Strategy<T> castStrategy = rawTypeStrategy;
    return Optional.of(castStrategy);
  }

}
