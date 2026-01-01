package pt.procurainterna.injection4j.invocation;

@FunctionalInterface
public interface FourArgumentsInvocation<T, A, B, C, D> {

  T invoke(A firstArgument, B secondArgument, C thirdArgument, D fourthArgument);

}
