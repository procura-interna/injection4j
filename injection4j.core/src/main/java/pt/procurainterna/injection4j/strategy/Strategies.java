package pt.procurainterna.injection4j.strategy;

import java.util.function.Supplier;
import pt.procurainterna.injection4j.invocation.FiveArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.FourArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.NoArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.OneArgumentInvocation;
import pt.procurainterna.injection4j.invocation.SixArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.ThreeArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.TwoArgumentsInvocation;
import pt.procurainterna.injection4j.invocation.VarargsInvocation;

public final class Strategies {

  private Strategies() {
  }

  public static <T> Strategy<T> fromValue(final T value) {
    return any -> value;
  }

  public static <T> Strategy<T> fromSupplier(final Supplier<T> supplier) {
    return any -> supplier.get();
  }

  public static <T> Strategy<? extends T> fromSynchronizedSingletonSupplier(
      final Supplier<T> supplier) {
    return new SynchronizedSingletonSupplierStrategy<>(supplier);
  }

  public static <T> Strategy<T> fromInvocation(NoArgumentsInvocation<? extends T> invocation) {
    return fetch -> invocation.invoke();
  }

  public static <T, A> Strategy<T> fromInvocation(
      OneArgumentInvocation<? extends T, ? super A> invocation, Class<A> argumentType) {
    return provider -> {
      final A argumentValue = provider.provide(argumentType);

      return invocation.invoke(argumentValue);
    };
  }

  public static <T, A, B> Strategy<T> fromInvocation(
      TwoArgumentsInvocation<? extends T, ? super A, ? super B> invocation, Class<A> firstArgument,
      Class<B> secondArgument) {
    return provider -> {
      final A firstValue = provider.provide(firstArgument);
      final B secondValue = provider.provide(secondArgument);

      return invocation.invoke(firstValue, secondValue);
    };
  }

  public static <T, A, B, C> Strategy<T> fromInvocation(
      ThreeArgumentsInvocation<? extends T, ? super A, ? super B, ? super C> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument) {
    return provider -> {
      final A firstValue = provider.provide(firstArgument);
      final B secondValue = provider.provide(secondArgument);
      final C thirdValue = provider.provide(thirdArgument);

      return invocation.invoke(firstValue, secondValue, thirdValue);
    };
  }

  public static <T, A, B, C, D> Strategy<T> fromInvocation(
      FourArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument) {
    return provider -> {
      final A firstValue = provider.provide(firstArgument);
      final B secondValue = provider.provide(secondArgument);
      final C thirdValue = provider.provide(thirdArgument);
      final D fourthValue = provider.provide(fourthArgument);

      return invocation.invoke(firstValue, secondValue, thirdValue, fourthValue);
    };
  }

  public static <T, A, B, C, D, E> Strategy<T> fromInvocation(
      FiveArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument, Class<E> fifthArgument) {
    return provider -> {
      final A firstValue = provider.provide(firstArgument);
      final B secondValue = provider.provide(secondArgument);
      final C thirdValue = provider.provide(thirdArgument);
      final D fourthValue = provider.provide(fourthArgument);
      final E fifthValue = provider.provide(fifthArgument);

      return invocation.invoke(firstValue, secondValue, thirdValue, fourthValue, fifthValue);
    };
  }

  public static <T, A, B, C, D, E, F> Strategy<T> fromInvocation(
      SixArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E, ? super F> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument, Class<E> fifthArgument, Class<F> sixthArgument) {
    return provider -> {
      final A firstValue = provider.provide(firstArgument);
      final B secondValue = provider.provide(secondArgument);
      final C thirdValue = provider.provide(thirdArgument);
      final D fourthValue = provider.provide(fourthArgument);
      final E fifthValue = provider.provide(fifthArgument);
      final F sixthValue = provider.provide(sixthArgument);

      return invocation.invoke(firstValue, secondValue, thirdValue, fourthValue, fifthValue,
          sixthValue);
    };
  }

  @SafeVarargs
  public static <T, A> Strategy<T> fromUnsafeInvocation(
      VarargsInvocation<? extends T, ? super A> invocation, Class<A>... arguments) {
    return provider -> {

      @SuppressWarnings("unchecked") final A[] values = (A[]) new Object[arguments.length];
      for (int i = 0, limit = values.length; i < limit; i++) {
        values[i] = provider.provide(arguments[i]);
      }

      return invocation.invoke(values);
    };
  }

}
