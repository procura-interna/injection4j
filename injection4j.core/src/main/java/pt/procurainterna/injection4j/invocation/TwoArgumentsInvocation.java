package pt.procurainterna.injection4j.invocation;

@FunctionalInterface
public interface TwoArgumentsInvocation<T, A, B> {

  T invoke(A firstArgument, B secondArgument);

}
