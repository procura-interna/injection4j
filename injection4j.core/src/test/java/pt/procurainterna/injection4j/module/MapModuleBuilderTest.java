package pt.procurainterna.injection4j.module;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import pt.procurainterna.injection4j.fetcher.Fetcher;
import pt.procurainterna.injection4j.invocation.FiveArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.FourArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.NoArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.OneArgumentInvocation;
import pt.procurainterna.injection4j.invocation.SixArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.ThreeArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.TwoArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.VarargsInvocation;
import pt.procurainterna.injection4j.strategy.Strategy;

class MapModuleBuilderTest {

  @Test
  void testBuild_CreatesModule() {
    final MapModuleBuilder builder = new MapModuleBuilder();
    builder.addValue(String.class, "test");
    
    final Module module = builder.build();
    final Optional<Strategy<String>> result = module.strategyFor(String.class);
    
    assertTrue(result.isPresent());
    assertEquals("test", result.get().execute(null));
  }

  @Test
  void testAddStrategy() {
    final MapModuleBuilder builder = new MapModuleBuilder();
    final Strategy<String> strategy = fetcher -> "test";
    
    builder.addStrategy(String.class, strategy);
    
    final Module module = builder.build();
    assertEquals(strategy, module.strategyFor(String.class).get());
  }

  @Test
  void testAddSupplier() {
    final MapModuleBuilder builder = new MapModuleBuilder();
    final Supplier<String> supplier = () -> "supplier";
    
    builder.addSupplier(String.class, supplier);
    
    final Module module = builder.build();
    assertEquals("supplier", module.strategyFor(String.class).get().execute(null));
  }

  @Test
  void testAddInvocation() {
    final MapModuleBuilder builder = new MapModuleBuilder();
    final NoArgumentsInvocation<String> invocation = () -> "invocation";
    
    builder.addInvocation(String.class, invocation);
    
    final Module module = builder.build();
    assertEquals("invocation", module.strategyFor(String.class).get().execute(null));
  }

  @Test
  void testAddInvocation_OneArgument() {
    final MapModuleBuilder builder = new MapModuleBuilder();
    final OneArgumentInvocation<String, Integer> invocation = (i) -> "param: " + i;

    builder.addInvocation(String.class, invocation, Integer.class);

    final Module module = builder.build();
    final Fetcher fetcher = new StubFetcher(Map.of(Integer.class, 123));

    assertEquals("param: 123", module.strategyFor(String.class).get().execute(fetcher));
  }

  @Test
  void testAddInvocation_TwoArguments() {
    final MapModuleBuilder builder = new MapModuleBuilder();
    final TwoArgumentsInvocation<String, Integer, Double> invocation = (i, d) -> "params: " + i + ", " + d;

    builder.addInvocation(String.class, invocation, Integer.class, Double.class);

    final Module module = builder.build();
    final Fetcher fetcher = new StubFetcher(Map.of(
      Integer.class, 1,
      Double.class, 2.0
    ));

    assertEquals("params: 1, 2.0", module.strategyFor(String.class).get().execute(fetcher));
  }

  @Test
  void testAddInvocation_ThreeArguments() {
    final MapModuleBuilder builder = new MapModuleBuilder();
    final ThreeArgumentsInvocation<String, Integer, Double, String> invocation = (i, d, s) -> "params: " + i + ", " + d + ", " + s;

    builder.addInvocation(String.class, invocation, Integer.class, Double.class, String.class);

    final Module module = builder.build();
    final Fetcher fetcher = new StubFetcher(Map.of(
      Integer.class, 1,
      Double.class, 2.0,
      String.class, "3"
    ));

    assertEquals("params: 1, 2.0, 3", module.strategyFor(String.class).get().execute(fetcher));
  }

  @Test
  void testAddInvocation_FourArguments() {
    final MapModuleBuilder builder = new MapModuleBuilder();
    final FourArgumentsInvocation<String, Integer, Double, String, Boolean> invocation = (i, d, s, b) -> "params: " + i + ", " + d + ", " + s + ", " + b;

    builder.addInvocation(String.class, invocation, Integer.class, Double.class, String.class, Boolean.class);

    final Module module = builder.build();
    final Fetcher fetcher = new StubFetcher(Map.of(
      Integer.class, 1,
      Double.class, 2.0,
      String.class, "3",
      Boolean.class, true
    ));

    assertEquals("params: 1, 2.0, 3, true", module.strategyFor(String.class).get().execute(fetcher));
  }

  @Test
  void testAddInvocation_FiveArguments() {
    final MapModuleBuilder builder = new MapModuleBuilder();
    final FiveArgumentsInvocation<String, Integer, Double, String, Boolean, Byte> invocation = (i, d, s, b, by) -> "params: " + i + ", " + d + ", " + s + ", " + b + ", " + by;

    builder.addInvocation(String.class, invocation, Integer.class, Double.class, String.class, Boolean.class, Byte.class);

    final Module module = builder.build();
    final Fetcher fetcher = new StubFetcher(Map.of(
      Integer.class, 1,
      Double.class, 2.0,
      String.class, "3",
      Boolean.class, true,
      Byte.class, (byte) 4
    ));

    assertEquals("params: 1, 2.0, 3, true, 4", module.strategyFor(String.class).get().execute(fetcher));
  }

  @Test
  void testAddInvocation_SixArguments() {
    final MapModuleBuilder builder = new MapModuleBuilder();
    final SixArgumentsInvocation<String, Integer, Double, String, Boolean, Byte, Short> invocation = (i, d, s, b, by, sh) -> "params: " + i + ", " + d + ", " + s + ", " + b + ", " + by + ", " + sh;

    builder.addInvocation(String.class, invocation, Integer.class, Double.class, String.class, Boolean.class, Byte.class, Short.class);

    final Module module = builder.build();
    final Fetcher fetcher = new StubFetcher(Map.of(
      Integer.class, 1,
      Double.class, 2.0,
      String.class, "3",
      Boolean.class, true,
      Byte.class, (byte) 4,
      Short.class, (short) 5
    ));

    assertEquals("params: 1, 2.0, 3, true, 4, 5", module.strategyFor(String.class).get().execute(fetcher));
  }

  @Test
  void testAddUnsafeInvocation() {
    final MapModuleBuilder builder = new MapModuleBuilder();
    final VarargsInvocation<String, Integer> invocation = (args) -> {
      int sum = 0;
      for (Integer i : args) {
        sum += i;
      }
      return "sum: " + sum;
    };

    builder.addUnsafeInvocation(String.class, invocation, Integer.class, Integer.class, Integer.class);

    final Module module = builder.build();
    // StubFetcher logic needs to handle multiple fetches of same type if implementation allows,
    // but the current MapModuleBuilder implementation calls fetch multiple times.
    // However, our StubFetcher uses a Map, which returns the same instance.
    // This is fine for this test as we just want to verify arguments are passed.
    final Fetcher fetcher = new StubFetcher(Map.of(Integer.class, 10));

    assertEquals("sum: 30", module.strategyFor(String.class).get().execute(fetcher));
  }

  private static class StubFetcher implements Fetcher {
    private final Map<Class<?>, Object> map;

    StubFetcher(Map<Class<?>, Object> map) {
      this.map = map;
    }

    @Override
    public <T> T fetch(Class<T> type) {
      return type.cast(map.get(type));
    }
  }}
