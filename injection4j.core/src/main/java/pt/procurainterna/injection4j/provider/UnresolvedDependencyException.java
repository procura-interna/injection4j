package pt.procurainterna.injection4j.provider;

public class UnresolvedDependencyException extends RuntimeException {

  private final Class<?> targetType;
  private final Class<?> dependencyType;

  public UnresolvedDependencyException(final Class<?> targetType, final Class<?> dependencyType, final Throwable cause) {
    super("While resolving " + targetType + ", failed to resolve dependency " + dependencyType, cause);
    this.targetType = targetType;
    this.dependencyType = dependencyType;
  }

  @SuppressWarnings("rawtypes")
  public Class targetType() {
    return targetType;
  }

  @SuppressWarnings("rawtypes")
  public Class dependencyType() {
    return dependencyType;
  }

}
