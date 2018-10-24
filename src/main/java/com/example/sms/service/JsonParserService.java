package com.example.sms.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
public interface JsonParserService<T> {

    /**
     * Method that convert provided file into DTO.
     *
     * @param file for conversion
     */
    T parse(MultipartFile file);
}
