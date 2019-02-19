package com.example.social.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.social.constant.ApplicationConstants.JOIN_DELIMITER;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@Getter
@Setter
@AllArgsConstructor
public class PairDto implements Comparable<PairDto> {
    private UserDto self;
    private UserDto partner;
    private Integer interestsNum;
    private List<String> interests;

    /**
     * Getter for partner's excluded field.
     *
     * @return true if partner still not excluded
     */
    @JsonIgnore
    public boolean isPartnerNotExcluded() {
        return !this.partner.isExcluded();
    }

    @Override
    public String toString() {
        return String.format("%s-%s[%s]", this.self, this.partner, this.getInterestsString());
    }

    /**
     * Special comparator that sort by number of common interests.
     * To allow adding of all elements it's never return equal result (0).
     * <p>
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final PairDto another) {
        final int result = another.getInterestsNum().compareTo(this.getInterestsNum());
        return result == 0 ? 1 : result;
    }

    /**
     * Special method that obtain and join list of interests that both of them contains.
     *
     * @return list of common interests
     */
    private String getInterestsString() {
        return this.interests.stream().collect(Collectors.joining(JOIN_DELIMITER));
    }
}
