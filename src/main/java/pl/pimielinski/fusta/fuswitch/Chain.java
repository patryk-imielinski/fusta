package pl.pimielinski.fusta.fuswitch;

import java.util.function.Predicate;

public class Chain<T> {

    private Predicate<T> chain;

    private Chain(Predicate<T> condition) {
        chain = condition;
    }

    public static <T> Chain<T> from(Predicate<T> condition) {
        return new Chain<>(condition);
    }

    public Chain<T> and(Predicate<? super T> condition) {
        chain = chain.and(condition);
        return this;
    }

    public Chain<T> or(Predicate<? super T> condition) {
        chain = chain.or(condition);
        return this;
    }

    public Chain<T> negate() {
        chain = chain.negate();
        return this;
    }
}
