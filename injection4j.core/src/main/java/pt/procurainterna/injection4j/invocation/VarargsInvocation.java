package pt.procurainterna.injection4j.invocation;

public interface VarargsInvocation<T, A> {

  T invoke(A... args);

}
