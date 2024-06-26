package pt.procurainterna.injection4j.module.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import pt.procurainterna.injection4j.invocation.FiveArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.FourArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.NoArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.OneArgumentInvocation;
import pt.procurainterna.injection4j.invocation.SixArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.ThreeArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.TwoArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.VarargsInvocation;
import pt.procurainterna.injection4j.module.MapModule;
import pt.procurainterna.injection4j.module.Module;
import pt.procurainterna.injection4j.strategy.Strategies;
import pt.procurainterna.injection4j.strategy.Strategy;

/**
 * Meant to aid in the creation of a {@link Module}. Uses a {@link Map} as its supporting data
 * structure.
 */
public class MapModuleBuilder implements ModuleBuilder {

  private final Map<Class<?>, Strategy<?>> map;

  public MapModuleBuilder(final Map<Class<?>, Strategy<?>> map) {
    this.map = map;
  }

  public MapModuleBuilder() {
    this(new HashMap<>());
  }

  @Override
  public Module build() {
    return new MapModule(new HashMap<>(map));
  }

  private <T> ModuleBuilder addToMap(final Class<T> type, final Strategy<? extends T> strategy) {
    map.put(type, strategy);

    return this;
  }

  @Override
  public <T> ModuleBuilder addStrategy(final Class<T> type, final Strategy<? extends T> strategy) {
    return addToMap(type, strategy);
  }

  @Override
  public <T> ModuleBuilder addValue(final Class<T> type, final T value) {
    final Strategy<T> strategy = Strategies.fromValue(value);

    return addToMap(type, strategy);
  }

  @Override
  public <T> ModuleBuilder addSupplier(final Class<T> type, final Supplier<? extends T> supplier) {
    final Strategy<? extends T> strategy = Strategies.fromSupplier(supplier);

    return addToMap(type, strategy);
  }

  @Override
  public <T> ModuleBuilder addSynchronizedSingletonSupplier(final Class<T> type,
      final Supplier<? extends T> supplier) {
    final Strategy<? extends T> strategy = Strategies.fromSynchronizedSingletonSupplier(supplier);

    return addToMap(type, strategy);
  }

  @Override
  public <T> ModuleBuilder addInvocation(final Class<T> type,
      final NoArgumentsInvocation<? extends T> invocation) {

    final Strategy<T> strategy = Strategies.fromInvocation(invocation);

    return addToMap(type, strategy);
  }

  @Override
  public <T, A> ModuleBuilder addInvocation(final Class<T> type,
      final OneArgumentInvocation<? extends T, ? super A> invocation, final Class<A> argumentType) {

    final Strategy<T> strategy = Strategies.fromInvocation(invocation, argumentType);

    return addToMap(type, strategy);
  }

  @Override
  public <T, A, B> ModuleBuilder addInvocation(final Class<T> type,
      final TwoArgumentsInvocation<? extends T, ? super A, ? super B> invocation,
      final Class<A> firstArgument, final Class<B> secondArgument) {

    final Strategy<T> strategy =
        Strategies.fromInvocation(invocation, firstArgument, secondArgument);

    return addToMap(type, strategy);
  }

  @Override
  public <T, A, B, C> ModuleBuilder addInvocation(final Class<T> type,
      final ThreeArgumentsInvocation<? extends T, ? super A, ? super B, ? super C> invocation,
      final Class<A> firstArgument, final Class<B> secondArgument, final Class<C> thirdArgument) {

    final Strategy<T> strategy =
        Strategies.fromInvocation(invocation, firstArgument, secondArgument, thirdArgument);

    return addToMap(type, strategy);
  }

  @Override
  public <T, A, B, C, D> ModuleBuilder addInvocation(final Class<T> type,
      final FourArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D> invocation,
      final Class<A> firstArgument, final Class<B> secondArgument, final Class<C> thirdArgument,
      final Class<D> fourthArgument) {

    final Strategy<T> strategy =
        Strategies.fromInvocation(invocation, firstArgument, secondArgument, thirdArgument,
            fourthArgument);

    return addToMap(type, strategy);
  }

  @Override
  public <T, A, B, C, D, E> ModuleBuilder addInvocation(final Class<T> type,
      final FiveArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E> invocation,
      final Class<A> firstArgument, final Class<B> secondArgument, final Class<C> thirdArgument,
      final Class<D> fourthArgument, final Class<E> fifthArgument) {

    final Strategy<T> strategy =
        Strategies.fromInvocation(invocation, firstArgument, secondArgument, thirdArgument,
            fourthArgument, fifthArgument);

    return addToMap(type, strategy);
  }

  @Override
  public <T, A, B, C, D, E, F> ModuleBuilder addInvocation(final Class<T> type,
      final SixArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E, ? super F> invocation,
      final Class<A> firstArgument, final Class<B> secondArgument, final Class<C> thirdArgument,
      final Class<D> fourthArgument, final Class<E> fifthArgument, final Class<F> sixthArgument) {

    final Strategy<T> strategy =
        Strategies.fromInvocation(invocation, firstArgument, secondArgument, thirdArgument,
            fourthArgument, fifthArgument, sixthArgument);

    return addToMap(type, strategy);
  }

  @Override
  public <T, A> ModuleBuilder addUnsafeInvocation(final Class<T> type,
      final VarargsInvocation<? extends T, ? super A> invocation, final Class<A>... arguments) {

    final Strategy<T> strategy = Strategies.fromUnsafeInvocation(invocation, arguments);

    return addToMap(type, strategy);
  }
}
