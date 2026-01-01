package pt.procurainterna.injection4j.module;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import pt.procurainterna.injection4j.strategy.Strategy;

class MapModuleTest {

  @Test
  void testStrategyFor_ReturnsEmpty_WhenMapIsEmpty() {
    final MapModule module = new MapModule(new HashMap<>());
    final Optional<Strategy<String>> result = module.strategyFor(String.class);

    assertTrue(result.isEmpty());
  }

  @Test
  void testStrategyFor_ReturnsStrategy_WhenMapContainsKey() {
    final HashMap<Class<?>, Strategy<?>> map = new HashMap<>();
    final Strategy<String> strategy = fetcher -> "test";
    map.put(String.class, strategy);

    final MapModule module = new MapModule(map);
    final Optional<Strategy<String>> result = module.strategyFor(String.class);

    assertTrue(result.isPresent());
    assertEquals(strategy, result.get());
  }
}
