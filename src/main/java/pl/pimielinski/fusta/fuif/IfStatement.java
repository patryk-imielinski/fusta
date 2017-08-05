package pl.pimielinski.fusta.fuif;

import pl.pimielinski.fusta.Statement;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface IfStatement<T, R> extends Statement<R> {

    IfStatement<T, R> elseIf(Function<? super T, ? extends R> action,
                             Predicate<? super T> condition);

    Optional<R> otherwise(Function<? super T, ? extends R> action);
}
