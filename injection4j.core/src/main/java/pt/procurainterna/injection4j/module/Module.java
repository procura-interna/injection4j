package pt.procurainterna.injection4j.module;

import java.util.Optional;
import pt.procurainterna.injection4j.strategy.Strategy;

/**
 * Meant to hold dependency injection configurations, that are related, in a modular way.
 */
public interface Module extends GeneralModule<Class<?>> {

  @SuppressWarnings({"rawtypes", "unchecked"})
  default <T> Optional<Strategy<T>> strategyFor(Class<?> type) {
    final Optional<? extends Strategy> strategy = strategyForType(type);
    return (Optional<Strategy<T>>) strategy;
  }

  <T> Optional<Strategy<T>> strategyForType(Class<T> type);

}
