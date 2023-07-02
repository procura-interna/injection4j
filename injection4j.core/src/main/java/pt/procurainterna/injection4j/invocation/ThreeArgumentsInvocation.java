package pt.procurainterna.injection4j.invocation;

@FunctionalInterface
public interface ThreeArgumentsInvocation<T, A, B, C> {

  T invoke(A firstArgument, B secondArgument, C thirdArgument);

}
