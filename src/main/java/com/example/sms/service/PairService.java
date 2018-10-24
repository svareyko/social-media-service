package com.example.sms.service;

import com.example.sms.dto.PairDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
public interface PairService {

    /**
     * Method that search all pairs from provided JSON.
     *
     * @param file JSON file that contains info about users
     * @return list of found pairs
     */
    List<PairDto> findPairs(final MultipartFile file);
}
