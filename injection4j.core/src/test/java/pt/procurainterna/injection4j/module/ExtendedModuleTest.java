package pt.procurainterna.injection4j.module;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import pt.procurainterna.injection4j.strategy.Strategy;

class ExtendedModuleTest {

  @Test
  void testStrategyFor_PrioritizesExtension() {
    final Strategy<String> baseStrategy = fetcher -> "base";
    final Strategy<String> extensionStrategy = fetcher -> "extension";

    final Module base = new Module() {
      @Override
      public <T> Optional<Strategy<T>> strategyFor(Class<T> type) {
         return Optional.of((Strategy<T>) baseStrategy);
      }
    };
    
    final Module extension = new Module() {
      @Override
      public <T> Optional<Strategy<T>> strategyFor(Class<T> type) {
         return Optional.of((Strategy<T>) extensionStrategy);
      }
    };

    final ExtendedModule module = new ExtendedModule(base, extension);

    Optional<Strategy<String>> result = module.strategyFor(String.class);
    
    assertTrue(result.isPresent());
    assertEquals(extensionStrategy, result.get());
  }

  @Test
  void testStrategyFor_FallsBackToBase_WhenExtensionMisses() {
    final Strategy<String> baseStrategy = fetcher -> "base";

    final Module base = new Module() {
      @Override
      public <T> Optional<Strategy<T>> strategyFor(Class<T> type) {
         return Optional.of((Strategy<T>) baseStrategy);
      }
    };
    final Module extension = new Module() {
      @Override
      public <T> Optional<Strategy<T>> strategyFor(Class<T> type) {
         return Optional.empty();
      }
    };

    final ExtendedModule module = new ExtendedModule(base, extension);

    Optional<Strategy<String>> result = module.strategyFor(String.class);

    assertTrue(result.isPresent());
    assertEquals(baseStrategy, result.get());
  }

  @Test
  void testStrategyFor_ReturnsEmpty_WhenBothMiss() {
    final Module base = new Module() {
      @Override
      public <T> Optional<Strategy<T>> strategyFor(Class<T> type) {
         return Optional.empty();
      }
    };
    final Module extension = new Module() {
      @Override
      public <T> Optional<Strategy<T>> strategyFor(Class<T> type) {
         return Optional.empty();
      }
    };

    final ExtendedModule module = new ExtendedModule(base, extension);

    Optional<Strategy<String>> result = module.strategyFor(String.class);

    assertTrue(result.isEmpty());
  }
}
