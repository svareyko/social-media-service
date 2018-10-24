package com.example.sms.service.impl;

import com.example.sms.dto.PairDto;
import com.example.sms.service.PairService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@Service
@AllArgsConstructor
public class PairServiceImpl implements PairService {

    @Override
    public List<PairDto> findPairs(final MultipartFile file) {
        // todo: parse and find pairs

        return Collections.emptyList();
    }
}
