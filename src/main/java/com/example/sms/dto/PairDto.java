package com.example.sms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@Getter
@Setter
public class PairDto {
    private UserDto first;
    private UserDto second;

    /**
     * Special method that obtain list of interests that both of them contains.
     *
     * @return list of common interests
     */
    public List<String> getInterests() {
        return first.getInterests().stream()
                .filter(interest -> second.getInterests().contains(interest))
                .collect(Collectors.toList());
    }
}
