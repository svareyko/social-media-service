package com.example.sms.service.impl;

import com.example.sms.dto.UserDto;
import com.example.sms.service.JsonParserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@Service
public class JsonParserServiceImpl implements JsonParserService<List<UserDto>> {
    @Override
    public List<UserDto> parse(final MultipartFile file) {
        return null;
    }
}
