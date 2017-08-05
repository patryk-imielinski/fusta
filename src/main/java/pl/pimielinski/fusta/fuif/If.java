package pl.pimielinski.fusta.fuif;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class If<T, R> implements IfStatement<T, R> {

    //private final T argument;
    //private final Set<Predicate<T>> elseIfs;

    @Override
    public synchronized IfStatement<T, R> elseIf(Function<? super T, ? extends R> action,
                                    Predicate<? super T> condition) {
        Objects.requireNonNull(action, "An 'action' can not be null");
        Objects.requireNonNull(condition, "A 'condition' can not be null");
        return this;
    }

    @Override
    public synchronized Optional<R> otherwise(Function<? super T, ? extends R> action) {
        return elseIf(action, arg -> true).execute();
    }

    @Override
    public synchronized Optional<R> execute() {
//        return elseIfs.stream()
//                .filter(elseIf -> elseIf.test(argument))
//                .findFirst()
//                .map(elseIf -> elseIf.evaluate(argument))
//                .orElse(Optional.empty());
        return null;
    }
}
