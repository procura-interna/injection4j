package pt.procurainterna.injection4j.module;

import java.util.Optional;
import pt.procurainterna.injection4j.strategy.Strategy;

public interface GeneralModule<K> {

  <T> Optional<Strategy<T>> strategyFor(K key);

}
