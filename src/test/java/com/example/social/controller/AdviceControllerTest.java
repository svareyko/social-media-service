package com.example.social.controller;

import com.example.social.exception.JsonParseException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.social.controller.AdviceController.JSON_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author s.vareyko
 * @since 25.10.2018
 */
public class AdviceControllerTest extends AbstractControllerTest {

    @Autowired
    private AdviceController adviceController;

    @Override
    Object getController() {
        return adviceController;
    }

    /**
     * {@link AdviceController#jsonParseExceptionHandler(com.example.social.exception.JsonParseException)}
     */
    @Test
    public void jsonParseException_directMethodTest() {
        final String exceptionMessage = "Test Exception";
        final JsonParseException exception = new JsonParseException(new RuntimeException(exceptionMessage));

        final String result = adviceController.jsonParseExceptionHandler(exception);
        assertThat(result).startsWith(JSON_EXCEPTION_MESSAGE);
        assertThat(result).contains(exceptionMessage);
    }
}
