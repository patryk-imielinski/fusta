package pl.pimielinski.fusta.fuswitch;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class SingleCase<T, R> implements Case<T, R> {

    private final Function<? super T, ? extends R> action;
    private final Predicate<? super T> predicate;

    private SingleCase(Function<? super T, ? extends R> action, Predicate<? super T> predicate) {
        this.action = action;
        this.predicate = predicate;
    }

    public static <T, R> Case<T, R> of(Function<? super T, ? extends R> action, Predicate<? super T> predicate) {
        return new SingleCase<>(action, predicate);
    }

    @Override
    public Optional<R> evaluate(T argument) {
        return null;
    }
}
