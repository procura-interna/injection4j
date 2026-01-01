package pt.procurainterna.injection4j.provider;

public class NoStrategyFoundException extends RuntimeException {

  public final Class<?> requestedType;

  public NoStrategyFoundException(final Class<?> requestedType) {
    super("No strategy found for " + requestedType);
    this.requestedType= requestedType;
  }

}
