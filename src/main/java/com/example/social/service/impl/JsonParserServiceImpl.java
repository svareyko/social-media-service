package com.example.social.service.impl;

import com.example.social.service.JsonParserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@Service
public class JsonParserServiceImpl implements JsonParserService {

    private ObjectMapper mapper;

    @PostConstruct
    public void init() {
        mapper = new ObjectMapper();
    }

    /**
     * Method that convert provided file into DTO.
     *
     * @param file for conversion
     * @param cls  class of target object
     * @return initialized with values from file object
     */
    @Override
    public <T> T parse(final MultipartFile file, final Class<T> cls) {
        try {
            return mapper.readValue(file.getInputStream(), cls);
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
