package com.example.social.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@Getter
@Setter
public class UserListDto {
    List<UserDto> list;
}
