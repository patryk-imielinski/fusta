package pl.pimielinski.fusta.fuswitch;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class ChainCase<T, R> implements Case<T, R> {

    private final Set<SingleCase<T, R>> cases;

    private ChainCase(Function<? super T, ? extends R> action,
                      Predicate<? super T> predicate,
                      Predicate<? super T>... predicates) {
        cases = new LinkedHashSet<>();
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
