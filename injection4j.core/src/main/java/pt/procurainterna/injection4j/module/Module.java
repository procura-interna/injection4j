package pt.procurainterna.injection4j.module;

import java.util.Optional;
import pt.procurainterna.injection4j.strategy.Strategy;

public interface Module {

  <T> Optional<Strategy<T>> strategyFor(Class<T> type);

}
