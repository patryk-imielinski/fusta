package pl.pimielinski.fusta.fuswitch;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

final class SingleCase<T, R> extends AbstractCase<T, R> {

    private final Predicate<? super T> condition;

    private SingleCase(Function<? super T, ? extends R> action, Predicate<? super T> condition) {
        super(action);
        this.condition = condition;
    }

    public static <T, R> Case<T, R> of(Function<? super T, ? extends R> action, Predicate<? super T> condition) {
        return new SingleCase<>(action, condition);
    }

    @Override
    public Optional<R> evaluate(T argument) {
        return Optional.ofNullable(action.apply(argument));
    }

    @Override
    public boolean matches(T argument) {
        return condition.test(argument);
    }
}
