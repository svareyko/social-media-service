package com.example.sms.service.impl;

import com.example.sms.dto.PairDto;
import com.example.sms.dto.UserListDto;
import com.example.sms.service.JsonParserService;
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

    private final JsonParserService jsonService;

    @Override
    public List<PairDto> findPairs(final MultipartFile file) {
        final UserListDto parsed = jsonService.parse(file, UserListDto.class);

        // todo: parse and find pairs

        return Collections.emptyList();
    }
}
