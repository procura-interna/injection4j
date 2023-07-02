package pt.procurainterna.injection4j.invocation;

@FunctionalInterface
public interface SixArgumentsInvocation<T, A, B, C, D, F, G> {

  T invoke(A firstArgument, B secondArgument, C thirdArgument, D fourthArgument, F fifthArgument,
      G sixthArgument);

}
