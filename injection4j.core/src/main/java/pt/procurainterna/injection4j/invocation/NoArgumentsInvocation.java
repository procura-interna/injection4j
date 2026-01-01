package pt.procurainterna.injection4j.invocation;

@FunctionalInterface
public interface NoArgumentsInvocation<T> {

  T invoke();

}
