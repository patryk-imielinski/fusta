package pl.pimielinski.fusta.fuswitch;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An implementation of a single case statement.
 *
 * @param <T> type of an argument of this case
 * @param <R> type of result value of this case
 */
final class SingleCase<T, R> implements Case<T, R> {

    private final Function<? super T, ? extends R> action;
    private final Set<T> conditions;

    @SafeVarargs
    SingleCase(Function<? super T, ? extends R> action,
              T firstCondition,
              T... rest) {
        this.action = action;
        List<T> filtered = Stream.of(rest)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        this.conditions = new LinkedHashSet<>();
        this.conditions.add(firstCondition);
        this.conditions.addAll(filtered);
    }

    @Override
    public Optional<R> evaluate(T argument) {
        if (matches(argument)) {
            return Optional.ofNullable(action.apply(argument));
        }

        throw new AssertionError("An 'argument' does not match condition");
    }

    @Override
    public boolean matches(final T argument) {
        return conditions.stream()
                .anyMatch(cond -> cond.equals(argument));
    }
}
