package pl.pimielinski.fusta.fuswitch;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

final class ChainCase<T, R> extends AbstractCase<T, R> {

    private final Set<Predicate<? super T>> conditions;

    private ChainCase(Function<? super T, ? extends R> action,
                      Predicate<? super T> condition,
                      Predicate<? super T>... conditions) {
        super(action);
        this.conditions = new LinkedHashSet<>();
        this.conditions.add(condition);
        Collections.addAll(this.conditions, conditions);
    }

    public static <T, R> Case<T, R> of(Function<? super T, ? extends R> action,
                                       Predicate<? super T> predicate,
                                       Predicate<? super T>... predicates) {
        return new ChainCase<>(action, predicate, predicates);
    }

    @Override
    public Optional<R> evaluate(T argument) {
        return null;
    }
}
