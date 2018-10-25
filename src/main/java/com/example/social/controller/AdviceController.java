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

    /**
     * Handle exceptions from database that generated when username already registered.
     *
     * @param exception thrown exception
     * @return error message written to the body
     */
    @ExceptionHandler(value = JsonParseException.class)
    @ResponseBody
    public String jsonParseExceptionHandler(final JsonParseException exception) {
        // todo: move such messages to internationalization
        return "Occurred exception during provided JSON file parsing: " + exception.getLocalizedMessage();
    }
}
