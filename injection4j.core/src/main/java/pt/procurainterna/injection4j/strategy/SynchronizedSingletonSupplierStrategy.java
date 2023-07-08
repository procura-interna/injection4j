package pt.procurainterna.injection4j.strategy;

import java.util.function.Supplier;
import pt.procurainterna.injection4j.fetcher.Fetcher;

public class SynchronizedSingletonSupplierStrategy<T> implements Strategy<T> {

  private final Object lock = new Object();

  private T value = null;
  private boolean valueHasBeenSet = false;

  private final Supplier<? extends T> supplier;

  public SynchronizedSingletonSupplierStrategy(final Supplier<? extends T> supplier) {
    this.supplier = supplier;
  }

  @Override
  public T execute(final Fetcher fetcher) {
    if (!valueHasBeenSet) {
      synchronizedSetValue();
    }

    return value;
  }

  private void synchronizedSetValue() {
    synchronized (lock) {
      if (!valueHasBeenSet) {
        value = supplier.get();
        valueHasBeenSet = true;
      }
    }
  }

}
