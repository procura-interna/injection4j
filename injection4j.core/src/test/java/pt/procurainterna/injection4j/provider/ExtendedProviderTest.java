package pt.procurainterna.injection4j.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class ExtendedProviderTest {

  @Test
  void testProvide_PrioritizesExtension() {
    final Provider base = new Provider() {
        @Override
        public <T> T provide(Class<T> type) {
            return (T) "base";
        }
    };
    final Provider extension = new Provider() {
        @Override
        public <T> T provide(Class<T> type) {
            return (T) "extension";
        }
    };

    final ExtendedProvider provider = new ExtendedProvider(base, extension);

    String result = provider.provide(String.class);
    assertEquals("extension", result);
  }

  @Test
  void testProvide_FallsBackToBase_WhenExtensionThrows() {
    final Provider base = new Provider() {
        @Override
        public <T> T provide(Class<T> type) {
            return (T) "base";
        }
    };
    final Provider extension = new Provider() {
        @Override
        public <T> T provide(Class<T> type) {
            throw new NoStrategyFoundException(type);
        }
    };

    final ExtendedProvider provider = new ExtendedProvider(base, extension);

    String result = provider.provide(String.class);
    assertEquals("base", result);
  }

  @Test
  void testProvide_Throws_WhenBothFail() {
    final Provider base = new Provider() {
        @Override
        public <T> T provide(Class<T> type) {
            throw new NoStrategyFoundException(type);
        }
    };
    final Provider extension = new Provider() {
        @Override
        public <T> T provide(Class<T> type) {
            throw new NoStrategyFoundException(type);
        }
    };

    final ExtendedProvider provider = new ExtendedProvider(base, extension);

    assertThrows(NoStrategyFoundException.class, () -> provider.provide(String.class));
  }
}
