package pl.pimielinski.fusta.fuswitch;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class Switch<T, R> implements SwitchStatement<T, R> {

    private final T argument;
    private final Set<Case<T, R>> cases;

    private Switch(T argument) {
        this.argument = argument;
        cases = new LinkedHashSet<>();
    }

    public static <T, R> SwitchStatement<T, R> of(T argument) {
        Objects.requireNonNull(argument, "An 'argument' can not be null");
        return new Switch<>(argument);
    }

    @Override
    public synchronized SwitchStatement singleCase(Function<? super T, ? extends R> action, Predicate<? super T> predicate) {
        cases.add(SingleCase.of(action, predicate));
        return this;
    }

    @SafeVarargs
    @Override
    public final synchronized SwitchStatement chainCase(Function<? super T, ? extends R> action,
                                                        Predicate<? super T> predicate,
                                                        Predicate<? super T>... predicates) {
        cases.add(ChainCase.of(action, predicate, predicates));
        return this;
    }

    @Override
    public final synchronized Optional<R> execute() {
        return null;
    }
}
