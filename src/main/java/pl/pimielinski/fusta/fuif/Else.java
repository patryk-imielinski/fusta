package pl.pimielinski.fusta.fuif;

import java.util.Optional;

/**
 * Represents a generic else-if block of an if statement.
 *
 * @param <T> type of an argument of this block
 * @param <R> type of result value of this block
 */
public interface Else<T, R> {

    /**
     * Evaluates this else-if returning optional result.
     *
     * @return an optional containing result of this block
     */
    Optional<R> evaluate(T argument);

    /**
     * Checks whether this block matches passed argument.
     *
     * @param argument a model value considered by this block
     * @return {@code true} if this block matches an argument, {@code false} otherwise
     */
    boolean matches(T argument);
}
