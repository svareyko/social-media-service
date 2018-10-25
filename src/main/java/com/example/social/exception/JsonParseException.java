package com.example.social.exception;

/**
 * @author s.vareyko
 * @since 25.10.2018
 */
public class JsonParseException extends RuntimeException {

    /**
     * Constructor which passes exception to runtime.
     *
     * @param exception originally thrown exception
     */
    public JsonParseException(final Exception exception) {
        super(exception);
    }
}
