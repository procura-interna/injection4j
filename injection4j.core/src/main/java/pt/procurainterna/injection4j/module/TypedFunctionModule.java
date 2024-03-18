package pt.procurainterna.injection4j.module;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import pt.procurainterna.injection4j.strategy.Strategy;

public class TypedFunctionModule extends FunctionModule<Class<?>> implements Module {

  public TypedFunctionModule(
      final Function<? super Class<?>, ? extends Strategy<?>> function) {
    super(function);
  }

  public TypedFunctionModule(final Map<Class<?>, Strategy<?>> map) {
    super(map);
  }

  @SuppressWarnings({"unchecked"})
  @Override
  public <T> Optional<Strategy<T>> strategyForType(final Class<T> type) {
    return strategyFor(type).map(s -> (Strategy<T>) s);
  }

}
