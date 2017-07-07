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
    public synchronized SwitchStatement<T, R> singleCase(Function<? super T, ? extends R> action,
                                                         Predicate<? super T> condition) {
        Objects.requireNonNull(action, "An 'action' can not be null");
        Objects.requireNonNull(condition, "A 'condition' can not be null");
        cases.add(SingleCase.of(action, condition));
        return this;
    }

    @SafeVarargs
    @Override
    public final synchronized SwitchStatement<T, R> chainCase(Function<? super T, ? extends R> action,
                                                              Predicate<? super T> firstCondition,
                                                              Predicate<? super T>... rest) {
        Objects.requireNonNull(action, "An 'action' can not be null");
        Objects.requireNonNull(firstCondition, "A 'firstCondition' can not be null");
        cases.add(ChainCase.of(action, firstCondition, rest));
        return this;
    }

    @Override
    public final synchronized Optional<R> execute() {
        for (Case<T, R> aCase : cases) {
            if (aCase.matches(argument)) {
                return aCase.evaluate(argument);
            }
        }

        return Optional.empty();
    }
}
