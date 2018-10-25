package com.example.social.service;

import com.example.social.dto.PairDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Pair lookup service, search pairs in provided JSON file.
 *
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
