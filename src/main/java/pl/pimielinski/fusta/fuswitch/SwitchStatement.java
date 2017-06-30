package pl.pimielinski.fusta.fuswitch;

import pl.pimielinski.fusta.Statement;

import java.util.function.Function;
import java.util.function.Predicate;

public interface SwitchStatement<T, R> extends Statement<R> {

    SwitchStatement singleCase(Function<? super T, ? extends R> action, Predicate<? super T> predicate);
    SwitchStatement chainCase(Function<? super T, ? extends R> action,
                              Predicate<? super T> predicate,
                              Predicate<? super T>... predicates);
}
