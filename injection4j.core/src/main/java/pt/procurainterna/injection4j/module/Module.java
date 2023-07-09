package pt.procurainterna.injection4j.module;

import java.util.Optional;
import pt.procurainterna.injection4j.strategy.Strategy;

/**
 * Meant to hold dependency injection configurations, that are related, in a modular way.
 */
public interface Module {

  <T> Optional<Strategy<T>> strategyFor(Class<T> type);

}
