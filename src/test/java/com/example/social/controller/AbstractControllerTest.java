package com.example.social.controller;

import com.example.social.AbstractTest;
import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @author s.vareyko
 * @since 25.10.2018
 */
public abstract class AbstractControllerTest extends AbstractTest {

    MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(getController())
                .setControllerAdvice(new AdviceController())
                .setViewResolvers((ViewResolver) (viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    abstract Object getController();
}
