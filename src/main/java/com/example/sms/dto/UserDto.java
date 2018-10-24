package com.example.sms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Container for received object.
 *
 * @author s.vareyko
 * @since 24.10.2018
 */
@Getter
@Setter
public class UserDto {
    private String name;
    private List<String> interests;
}