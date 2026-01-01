package pt.procurainterna.injection4j.invocation;

@FunctionalInterface
public interface FiveArgumentsInvocation<T, A, B, C, D, F> {

  T invoke(A firstArgument, B secondArgument, C thirdArgument, D fourthArgument, F fifthArgument);

}
