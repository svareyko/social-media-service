package com.example.social.controller;

import com.example.social.exception.JsonParseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author s.vareyko
 * @since 25.10.2018
 */
@ControllerAdvice
public class AdviceController {

    // todo: move such messages to internationalization
    final static String JSON_EXCEPTION_MESSAGE = "Occurred exception during provided JSON file parsing: ";

    /**
     * Handle exceptions thrown by JSON parser.
     *
     * @param exception thrown exception
     * @return error message written to the body
     */
    @ExceptionHandler(value = JsonParseException.class)
    @ResponseBody
    public String jsonParseExceptionHandler(final JsonParseException exception) {
        return JSON_EXCEPTION_MESSAGE + exception.getLocalizedMessage();
    }
}
