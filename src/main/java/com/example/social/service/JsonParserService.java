package com.example.social.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
public interface JsonParserService {

    /**
     * Method that convert provided file into DTO.
     *
     * @param file for conversion
     * @param cls  class of target object
     * @return initialized with values from file object
     */
    <T> T parse(final MultipartFile file, final Class<T> cls);
}
