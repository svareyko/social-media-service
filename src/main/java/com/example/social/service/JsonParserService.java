package com.example.social.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * JSON parser service handles operations with JSON.
 *
 * @author s.vareyko
 * @since 24.10.2018
 */
public interface JsonParserService {

    /**
     * Method that read provided {@link org.springframework.web.multipart.MultipartFile}
     * into instance of provided class.
     *
     * @param file for conversion
     * @param cls  class of target object
     * @param <T>  generic type for return statement
     * @return initialized with values from file object
     */
    <T> T parse(final MultipartFile file, final Class<T> cls);
}
