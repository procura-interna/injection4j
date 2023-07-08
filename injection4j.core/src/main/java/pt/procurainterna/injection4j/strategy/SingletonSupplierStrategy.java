package pt.procurainterna.injection4j.strategy;

import java.util.function.Supplier;
import pt.procurainterna.injection4j.fetcher.Fetcher;

public class SingletonSupplierStrategy<T> implements Strategy<T> {

  private T value = null;
  private boolean valueHasBeenSet = false;

  private final Supplier<? extends T> supplier;

  public SingletonSupplierStrategy(final Supplier<? extends T> supplier) {
    this.supplier = supplier;
  }

  @Override
  public T execute(final Fetcher fetcher) {
    if (!valueHasBeenSet) {
      value = supplier.get();
      valueHasBeenSet = true;
    }

    return value;
  }

}
