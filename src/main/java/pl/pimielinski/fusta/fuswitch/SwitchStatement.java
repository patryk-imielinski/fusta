package pl.pimielinski.fusta.fuswitch;

import pl.pimielinski.fusta.Statement;

import java.util.Optional;
import java.util.function.Function;

/**
 * Represents an executable switch statement.
 *
 * @param <T> type of an argument of this statement
 * @param <R> type of result value of this statement
 */
public interface SwitchStatement<T, R> extends Statement<R> {

    /**
     * Represents a single case statement.
     *
     * @param action an action to be executed during evaluating of this case
     * @param firstCondition a mandatory condition value
     * @param rest optional conditional values evaluated with OR function
     * @return this statement
     */
    SwitchStatement<T, R> singleCase(Function<? super T, ? extends R> action,
                                    T firstCondition,
                                    T... rest);

    /**
     * A default action if none case matches specific condition.
     * Note that this method has to be put at the end of switch
     * statement and thus it should evaluate statement immediately.
     *
     * @param action an action to be executed during evaluating of this case
     * @return an optional containing result of this statement
     */
    Optional<R> byDefault(Function<? super T, ? extends R> action);
}
