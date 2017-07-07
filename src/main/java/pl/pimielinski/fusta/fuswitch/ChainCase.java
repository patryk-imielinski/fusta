package pl.pimielinski.fusta.fuswitch;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

final class ChainCase<T, R> extends AbstractCase<T, R> {

    private final Set<Predicate<? super T>> conditions;

    private ChainCase(Function<? super T, ? extends R> action,
                      Predicate<? super T> firstCondition,
                      Predicate<? super T>... rest) {
        super(action);
        conditions = new LinkedHashSet<>();
        conditions.add(firstCondition);
        Collections.addAll(conditions, rest);
    }

    public static <T, R> Case<T, R> of(Function<? super T, ? extends R> action,
                                       Predicate<? super T> firstCondition,
                                       Predicate<? super T>... rest) {
        return (rest.length == 0)
                ? SingleCase.of(action, firstCondition)
                : ChainCase.of(action, firstCondition, rest);
    }

    @Override
    public Optional<R> evaluate(T argument) {
        return null;
    }

    @Override
    public boolean matches(T argument) {
        return false;
    }
}
