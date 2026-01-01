package pt.procurainterna.injection4j.invocation;

@FunctionalInterface
public interface OneArgumentInvocation<T, A> {

  T invoke(A argument);

}
