package pt.procurainterna.injection4j.module.builder;

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
import pt.procurainterna.injection4j.strategy.Strategy;

public interface GeneralModuleBuilder<K> {

  <T> GeneralModuleBuilder<K> addStrategy(K key, Strategy<T> strategy);

  <T> GeneralModuleBuilder addValue(K key, T value);

  <T> GeneralModuleBuilder addSupplier(K key, Supplier<T> supplier);

  <T> GeneralModuleBuilder addSynchronizedSingletonSupplier(K key,
      Supplier<? extends T> supplier);

  <T> GeneralModuleBuilder addInvocation(K key, NoArgumentsInvocation<? extends T> invocation);

  <T, A> GeneralModuleBuilder addInvocation(K key,
      OneArgumentInvocation<? extends T, ? super A> invocation, Class<A> argumentType);

  <T, A, B> GeneralModuleBuilder addInvocation(K key,
      TwoArgumentsInvocation<? extends T, ? super A, ? super B> invocation, Class<A> firstArgument,
      Class<B> secondArgument);

  <T, A, B, C> GeneralModuleBuilder addInvocation(K key,
      ThreeArgumentsInvocation<? extends T, ? super A, ? super B, ? super C> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument);

  <T, A, B, C, D> GeneralModuleBuilder addInvocation(K key,
      FourArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument);

  <T, A, B, C, D, E> GeneralModuleBuilder addInvocation(K key,
      FiveArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument, Class<E> fifthArgument);

  <T, A, B, C, D, E, F> GeneralModuleBuilder addInvocation(K key,
      SixArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E, ? super F> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument, Class<E> fifthArgument, Class<F> sixthArgument);

  <T, A> GeneralModuleBuilder addUnsafeInvocation(K key, VarargsInvocation<? extends T, ? super A> invocation,
      Class<A>... arguments);

  GeneralModule<K> build();

}
