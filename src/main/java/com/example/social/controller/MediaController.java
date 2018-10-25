package com.example.social.controller;

import com.example.social.dto.PairDto;
import com.example.social.service.PairService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.social.constant.ApplicationConstants.URL_ROOT;
import static com.example.social.constant.ApplicationConstants.URL_UPLOAD;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@RestController
@AllArgsConstructor
@RequestMapping(URL_ROOT)
public class MediaController {

    private final PairService service;

    /**
     * Upload handler, expects JSON file.
     *
     * @param file multipart JSON file
     * @return list of found pairs
     */
    @PostMapping(path = URL_UPLOAD, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<PairDto> upload(final MultipartFile file) {
        return service.findPairs(file);
    }
}
