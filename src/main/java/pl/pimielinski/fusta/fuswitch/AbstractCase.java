package pl.pimielinski.fusta.fuswitch;

import java.util.function.Function;

public abstract class AbstractCase<T, R> implements Case<T, R> {

    protected final Function<? super T, ? extends R> action;

    public AbstractCase(Function<? super T, ? extends R> action) {
        this.action = action;
    }
}
