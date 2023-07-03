package pt.procurainterna.injection4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.procurainterna.injection4j.module.MapModuleBuilder;
import pt.procurainterna.injection4j.module.Module;
import pt.procurainterna.injection4j.module.ModuleBuilder;
import pt.procurainterna.injection4j.provider.Provider;
import pt.procurainterna.injection4j.provider.RecursiveModuleProvider;
import pt.procurainterna.injection4j.provider.UnresolvedDependencyException;

class SimpleTest {

  private static final String FIRST_DEPENDENCY_VALUE = "Foo";
  private static final int SECOND_DEPENDENCY_VALUE = 42;

  @Test
  void testCorrectConfiguration() {
    final ModuleBuilder moduleBuilder = new MapModuleBuilder();

    moduleBuilder.addValue(String.class, FIRST_DEPENDENCY_VALUE);
    moduleBuilder.addValue(Integer.class, SECOND_DEPENDENCY_VALUE);

    moduleBuilder.addInvocation(ClassWithDependencies.class, ClassWithDependencies::new, String.class, Integer.class);

    final Module module = moduleBuilder.build();

    final Provider provider = new RecursiveModuleProvider(module);

    final ClassWithDependencies provided = provider.provide(ClassWithDependencies.class);

    Assertions.assertSame(FIRST_DEPENDENCY_VALUE, provided.string);
    Assertions.assertSame(SECOND_DEPENDENCY_VALUE, provided.integer);
  }

  @Test
  void testInCorrectConfiguration() {
    final ModuleBuilder moduleBuilder = new MapModuleBuilder();

    moduleBuilder.addValue(String.class, FIRST_DEPENDENCY_VALUE);

    final Class<Integer> missingDependencyType = Integer.class;
    moduleBuilder.addInvocation(ClassWithDependencies.class, ClassWithDependencies::new, String.class, missingDependencyType);

    final Module module = moduleBuilder.build();

    final Provider provider = new RecursiveModuleProvider(module);

    final UnresolvedDependencyException exception =
        Assertions.assertThrows(UnresolvedDependencyException.class,
            () -> provider.provide(ClassWithDependencies.class));

    Assertions.assertEquals(ClassWithDependencies.class, exception.targetType());
    Assertions.assertEquals(missingDependencyType, exception.dependencyType());
  }


  public static class ClassWithDependencies {

    public final String string;
    public final Integer integer;

    public ClassWithDependencies(final String string, final Integer integer) {
      this.string = string;
      this.integer = integer;
    }
  }

}
