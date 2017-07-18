package pl.pimielinski.fusta.fuswitch;

import java.util.Optional;

/**
 * Represents a generic case of a switch statement.
 *
 * @param <T> type of an argument of this case
 * @param <R> type of result value of this case
 */
public interface Case<T, R> {

    /**
     * Evaluates this case returning optional result.
     *
     * @return an optional containing result of this case
     */
    Optional<R> evaluate(T argument);

    /**
     * Checks whether this case matches passed argument.
     *
     * @param argument a model value considered by this case
     * @return {@code true} if this case matches an argument, {@code false} otherwise
     */
    boolean matches(T argument);
}
