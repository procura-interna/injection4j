package pt.procurainterna.injection4j.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import pt.procurainterna.injection4j.module.Module;
import pt.procurainterna.injection4j.strategy.Strategy;

class RecursiveModuleProviderTest {

  @Test
  void testProvide_ThrowsException_WhenNoStrategyFound() {
    final Module emptyModule = new Module() {
      @Override
      public <T> Optional<Strategy<T>> strategyFor(Class<T> type) {
        return Optional.empty();
      }
    };
    final RecursiveModuleProvider provider = new RecursiveModuleProvider(emptyModule);

    assertThrows(NoStrategyFoundException.class, () -> provider.provide(String.class));
  }

  @Test
  void testProvide_ReturnsValue_WhenStrategyExists() {
    final Module module = new Module() {
      @Override
      public <T> Optional<Strategy<T>> strategyFor(Class<T> type) {
        if (type.equals(String.class)) {
          Strategy<String> s = fetcher -> "test";
          return Optional.of((Strategy<T>) s);
        }
        return Optional.empty();
      }
    };
    final RecursiveModuleProvider provider = new RecursiveModuleProvider(module);

    assertEquals("test", provider.provide(String.class));
  }

  @Test
  void testProvide_ResolvesDependencies() {
    final Module module = new Module() {
      @Override
      public <T> Optional<Strategy<T>> strategyFor(Class<T> type) {
        if (type.equals(String.class)) {
          Strategy<String> s = fetcher -> "A" + fetcher.fetch(Integer.class);
          return Optional.of((Strategy<T>) s);
        }
        if (type.equals(Integer.class)) {
          Strategy<Integer> s = fetcher -> 1;
          return Optional.of((Strategy<T>) s);
        }
        return Optional.empty();
      }
    };

    final RecursiveModuleProvider provider = new RecursiveModuleProvider(module);
    assertEquals("A1", provider.provide(String.class));
  }

  @Test
  void testProvide_ThrowsUnresolvedDependencyException_WhenDependencyMissing() {
    final Module module = new Module() {
      @Override
      public <T> Optional<Strategy<T>> strategyFor(Class<T> type) {
        if (type.equals(String.class)) {
          Strategy<String> s = fetcher -> "A" + fetcher.fetch(Integer.class);
          return Optional.of((Strategy<T>) s);
        }
        return Optional.empty();
      }
    };

    final RecursiveModuleProvider provider = new RecursiveModuleProvider(module);

    assertThrows(UnresolvedDependencyException.class, () -> provider.provide(String.class));
  }
}
