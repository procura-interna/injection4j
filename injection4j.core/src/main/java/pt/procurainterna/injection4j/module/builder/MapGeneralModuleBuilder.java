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
import pt.procurainterna.injection4j.module.GeneralModule;
import pt.procurainterna.injection4j.module.MapGeneralModule;
import pt.procurainterna.injection4j.module.MapModule;
import pt.procurainterna.injection4j.module.Module;
import pt.procurainterna.injection4j.strategy.Strategies;
import pt.procurainterna.injection4j.strategy.Strategy;

/**
 * Meant to aid in the creation of a {@link Module}. Uses a {@link Map} as its supporting data
 * structure.
 */
public class MapGeneralModuleBuilder<K> implements GeneralModuleBuilder<K> {

  private final Map<K, Strategy<?>> map;

  public MapGeneralModuleBuilder(final Map<K, Strategy<?>> map) {
    this.map = map;
  }

  public MapGeneralModuleBuilder() {
    this(new HashMap<>());
  }

  @Override
  public GeneralModule<K> build() {
    return new MapGeneralModule<>(new HashMap<>(map));
  }

  private <T> GeneralModuleBuilder<K> addToMap(K key, final Strategy<? extends T> strategy) {
    map.put(key, strategy);

    return this;
  }

  @Override
  public <T> GeneralModuleBuilder<K> addStrategy(final K key, final Strategy<T> strategy) {
    return addToMap(key, strategy);
  }

  @Override
  public <T> GeneralModuleBuilder<K> addValue(K key, final T value) {
    final Strategy<T> strategy = Strategies.fromValue(value);

    return addToMap(key, strategy);
  }

  @Override
  public <T> GeneralModuleBuilder<K> addSupplier(K key, final Supplier<T> supplier) {
    final Strategy<? extends T> strategy = Strategies.fromSupplier(supplier);

    return addToMap(key, strategy);
  }

  @Override
  public <T> GeneralModuleBuilder<K> addSynchronizedSingletonSupplier(K key,
      final Supplier<? extends T> supplier) {
    final Strategy<? extends T> strategy = Strategies.fromSynchronizedSingletonSupplier(supplier);

    return addToMap(key, strategy);
  }

  @Override
  public <T> GeneralModuleBuilder<K> addInvocation(K key,
      final NoArgumentsInvocation<? extends T> invocation) {

    final Strategy<T> strategy = Strategies.fromInvocation(invocation);

    return addToMap(key, strategy);
  }

  @Override
  public <T, A> GeneralModuleBuilder<K> addInvocation(K key,
      final OneArgumentInvocation<? extends T, ? super A> invocation, final Class<A> argumentType) {

    final Strategy<T> strategy = Strategies.fromInvocation(invocation, argumentType);

    return addToMap(key, strategy);
  }

  @Override
  public <T, A, B> GeneralModuleBuilder<K> addInvocation(K key,
      final TwoArgumentsInvocation<? extends T, ? super A, ? super B> invocation,
      final Class<A> firstArgument, final Class<B> secondArgument) {

    final Strategy<T> strategy =
        Strategies.fromInvocation(invocation, firstArgument, secondArgument);

    return addToMap(key, strategy);
  }

  @Override
  public <T, A, B, C> GeneralModuleBuilder<K> addInvocation(K key,
      final ThreeArgumentsInvocation<? extends T, ? super A, ? super B, ? super C> invocation,
      final Class<A> firstArgument, final Class<B> secondArgument, final Class<C> thirdArgument) {

    final Strategy<T> strategy =
        Strategies.fromInvocation(invocation, firstArgument, secondArgument, thirdArgument);

    return addToMap(key, strategy);
  }

  @Override
  public <T, A, B, C, D> GeneralModuleBuilder<K> addInvocation(K key,
      final FourArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D> invocation,
      final Class<A> firstArgument, final Class<B> secondArgument, final Class<C> thirdArgument,
      final Class<D> fourthArgument) {

    final Strategy<T> strategy =
        Strategies.fromInvocation(invocation, firstArgument, secondArgument, thirdArgument,
            fourthArgument);

    return addToMap(key, strategy);
  }

  @Override
  public <T, A, B, C, D, E> GeneralModuleBuilder<K> addInvocation(K key,
      final FiveArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E> invocation,
      final Class<A> firstArgument, final Class<B> secondArgument, final Class<C> thirdArgument,
      final Class<D> fourthArgument, final Class<E> fifthArgument) {

    final Strategy<T> strategy =
        Strategies.fromInvocation(invocation, firstArgument, secondArgument, thirdArgument,
            fourthArgument, fifthArgument);

    return addToMap(key, strategy);
  }

  @Override
  public <T, A, B, C, D, E, F> GeneralModuleBuilder<K> addInvocation(K key,
      final SixArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E, ? super F> invocation,
      final Class<A> firstArgument, final Class<B> secondArgument, final Class<C> thirdArgument,
      final Class<D> fourthArgument, final Class<E> fifthArgument, final Class<F> sixthArgument) {

    final Strategy<T> strategy =
        Strategies.fromInvocation(invocation, firstArgument, secondArgument, thirdArgument,
            fourthArgument, fifthArgument, sixthArgument);

    return addToMap(key, strategy);
  }

  @Override
  public <T, A> GeneralModuleBuilder<K> addUnsafeInvocation(K key,
      final VarargsInvocation<? extends T, ? super A> invocation, final Class<A>... arguments) {

    final Strategy<T> strategy = Strategies.fromUnsafeInvocation(invocation, arguments);

    return addToMap(key, strategy);
  }
}
