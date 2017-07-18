package pl.pimielinski.fusta;

import java.util.Optional;

/**
 * Represents a generic executable statement.
 *
 * @param <R> type of result value of this statement
 */
public interface Statement<R> {

    /**
     * Executes this statement returning optional result.
     *
     * @return an optional containing result of this statement
     */
    Optional<R> execute();
}
