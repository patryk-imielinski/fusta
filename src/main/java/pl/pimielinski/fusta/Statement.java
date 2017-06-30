package pl.pimielinski.fusta;

import java.util.Optional;

public interface Statement<R> {

    Optional<R> execute();
}
