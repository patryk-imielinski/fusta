package pl.pimielinski.fusta.fuswitch;

import java.util.Optional;

public interface Case<T, R> {

    Optional<R> evaluate(T argument);
    boolean matches(T argument);
}
