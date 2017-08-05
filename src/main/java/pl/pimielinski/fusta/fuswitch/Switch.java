package pl.pimielinski.fusta.fuswitch;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * An implementation of a switch statement.
 *
 * @param <T> type of an argument of this statement
 * @param <R> type of result value of this statement
 */
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

    @SafeVarargs
    @Override
    public final synchronized SwitchStatement<T, R> singleCase(Function<? super T, ? extends R> action,
                                                               T firstCondition,
                                                               T... rest) {
        Objects.requireNonNull(action, "An 'action' can not be null");
        Objects.requireNonNull(firstCondition, "A 'firstCondition' can not be null");
        cases.add(new SingleCase<>(action, firstCondition, rest));
        return this;
    }

    @Override
    public final synchronized Optional<R> byDefault(Function<? super T, ? extends R> action) {
        return singleCase(action, argument).execute();
    }

    @Override
    public final synchronized Optional<R> execute() {
        return cases.stream()
                .filter(singleCase -> singleCase.matches(argument))
                .findFirst()
                .map(singleCase -> singleCase.evaluate(argument))
                .orElse(Optional.empty());
    }
}
