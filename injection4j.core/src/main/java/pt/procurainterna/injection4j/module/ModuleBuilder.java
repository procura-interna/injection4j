package pt.procurainterna.injection4j.module;

import java.util.function.Supplier;
import pt.procurainterna.injection4j.invocation.FiveArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.FourArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.NoArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.OneArgumentInvocation;
import pt.procurainterna.injection4j.invocation.SixArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.ThreeArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.TwoArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.VarargsInvocation;
import pt.procurainterna.injection4j.strategy.Strategy;

public interface ModuleBuilder {

  Module build();

  <T> ModuleBuilder addStrategy(Class<T> type, Strategy<? extends T> strategy);

  <T> ModuleBuilder addValue(Class<T> type, T value);

  <T> ModuleBuilder addSupplier(Class<T> type, Supplier<? extends T> supplier);

  <T> ModuleBuilder addInvocation(Class<T> type, NoArgumentsInvocation<? extends T> invocation);

  <T, A> ModuleBuilder addInvocation(Class<T> type,
      OneArgumentInvocation<? extends T, ? super A> invocation, Class<A> argumentType);

  <T, A, B> ModuleBuilder addInvocation(Class<T> type,
      TwoArgumentsInvocation<? extends T, ? super A, ? super B> invocation, Class<A> firstArgument,
      Class<B> secondArgument);

  <T, A, B, C> ModuleBuilder addInvocation(Class<T> type,
      ThreeArgumentsInvocation<? extends T, ? super A, ? super B, ? super C> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument);

  <T, A, B, C, D> ModuleBuilder addInvocation(Class<T> type,
      FourArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument);

  <T, A, B, C, D, E> ModuleBuilder addInvocation(Class<T> type,
      FiveArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument, Class<E> fifthArgument);

  <T, A, B, C, D, E, F> ModuleBuilder addInvocation(Class<T> type,
      SixArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E, ? super F> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument, Class<E> fifthArgument, Class<F> sixthArgument);

  <T, A> ModuleBuilder addUnsafeInvocation(Class<T> type, VarargsInvocation<? extends T, ? super A> invocation,
      Class<A>... arguments);

}
