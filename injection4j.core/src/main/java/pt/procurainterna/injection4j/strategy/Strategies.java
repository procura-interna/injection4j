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

  public static <T> Strategy<T> fromInvocation(Class<T> type,
      NoArgumentsInvocation<? extends T> invocation) {
    return fetch -> invocation.invoke();
  }

  public static <T, A> Strategy<T> fromInvocation(Class<T> type,
      OneArgumentInvocation<? extends T, ? super A> invocation, Class<A> argumentType) {
    return fetch -> {
      final A argumentValue = fetch.fetch(argumentType);

      return invocation.invoke(argumentValue);
    };
  }

  public static <T, A, B> Strategy<T> fromInvocation(Class<T> type,
      TwoArgumentsInvocation<? extends T, ? super A, ? super B> invocation, Class<A> firstArgument,
      Class<B> secondArgument) {
    return fetch -> {
      final A firstValue = fetch.fetch(firstArgument);
      final B secondValue = fetch.fetch(secondArgument);

      return invocation.invoke(firstValue, secondValue);
    };
  }

  public static <T, A, B, C> Strategy<T> fromInvocation(Class<T> type,
      ThreeArgumentsInvocation<? extends T, ? super A, ? super B, ? super C> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument) {
    return fetch -> {
      final A firstValue = fetch.fetch(firstArgument);
      final B secondValue = fetch.fetch(secondArgument);
      final C thirdValue = fetch.fetch(thirdArgument);

      return invocation.invoke(firstValue, secondValue, thirdValue);
    };
  }

  public static <T, A, B, C, D> Strategy<T> fromInvocation(Class<T> type,
      FourArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument) {
    return fetch -> {
      final A firstValue = fetch.fetch(firstArgument);
      final B secondValue = fetch.fetch(secondArgument);
      final C thirdValue = fetch.fetch(thirdArgument);
      final D fourthValue = fetch.fetch(fourthArgument);

      return invocation.invoke(firstValue, secondValue, thirdValue, fourthValue);
    };
  }

  public static <T, A, B, C, D, E> Strategy<T> fromInvocation(Class<T> type,
      FiveArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument, Class<E> fifthArgument) {
    return fetch -> {
      final A firstValue = fetch.fetch(firstArgument);
      final B secondValue = fetch.fetch(secondArgument);
      final C thirdValue = fetch.fetch(thirdArgument);
      final D fourthValue = fetch.fetch(fourthArgument);
      final E fifthValue = fetch.fetch(fifthArgument);

      return invocation.invoke(firstValue, secondValue, thirdValue, fourthValue, fifthValue);
    };
  }

  public static <T, A, B, C, D, E, F> Strategy<T> fromInvocation(Class<T> type,
      SixArgumentsInvocation<? extends T, ? super A, ? super B, ? super C, ? super D, ? super E, ? super F> invocation,
      Class<A> firstArgument, Class<B> secondArgument, Class<C> thirdArgument,
      Class<D> fourthArgument, Class<E> fifthArgument, Class<F> sixthArgument) {
    return fetch -> {
      final A firstValue = fetch.fetch(firstArgument);
      final B secondValue = fetch.fetch(secondArgument);
      final C thirdValue = fetch.fetch(thirdArgument);
      final D fourthValue = fetch.fetch(fourthArgument);
      final E fifthValue = fetch.fetch(fifthArgument);
      final F sixthValue = fetch.fetch(sixthArgument);

      return invocation.invoke(firstValue, secondValue, thirdValue, fourthValue, fifthValue,
          sixthValue);
    };
  }

  @SafeVarargs
  public static <T, A> Strategy<T> fromUnsafeInvocation(Class<T> type,
      VarargsInvocation<? extends T, ? super A> invocation, Class<A>... arguments) {
    return fetch -> {

      @SuppressWarnings("unchecked") final A[] values = (A[]) new Object[arguments.length];
      for (int i = 0, limit = values.length; i < limit; i++) {
        values[i] = fetch.fetch(arguments[i]);
      }

      return invocation.invoke(values);
    };
  }
}
