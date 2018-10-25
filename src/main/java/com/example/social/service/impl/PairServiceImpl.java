package com.example.social.service.impl;

import com.example.social.dto.PairDto;
import com.example.social.dto.UserDto;
import com.example.social.dto.UserListDto;
import com.example.social.service.JsonParserService;
import com.example.social.service.PairService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
        // as we work only with one instance of UserDto we don't need hashcode & equals
        final List<UserDto> users = parsed.getList();
        final Map<UserDto, Set<PairDto>> pairsByUser = collectPairsByUser(users);
        return collectPairsByCommonInterests(users, pairsByUser);
    }

    /**
     * Helper method that collect list of pairs for each user.
     * Additionally pairs sorted by number of common interests (used sorting in {@link TreeSet}).
     *
     * @param users list of users with their interests
     * @return mapped interests by user dto
     * @see java.util.TreeSet
     */
    private Map<UserDto, Set<PairDto>> collectPairsByUser(final List<UserDto> users) {
        final Map<UserDto, Set<PairDto>> result = new HashMap<>();
        // initialize array with tree sets
        users.forEach(user -> result.put(user, new TreeSet<PairDto>()));

        // iterate over all users and collect all possible pairs
        for (int i = 0; i < users.size(); i++) {
            final UserDto first = users.get(i);

            // decrease number of iterations by excluding already checked pairs
            for (int j = i + 1; j < users.size(); j++) {
                final UserDto second = users.get(j);
                final List<String> intSecond = second.getInterests();
                final List<String> common = first.getInterests().stream()
                        .filter(intSecond::contains).collect(Collectors.toList());
                final int size = common.size();
                // add only if there is common interests
                if (size > 0) {
                    result.get(first).add(new PairDto(first, second, size, common));
                    result.get(second).add(new PairDto(second, first, size, common));
                }
            }
        }
        return result;
    }

    /**
     * Helper method that iterate all users and looks for best matching pairs.
     *
     * @param users       list of users with their interests
     * @param pairsByUser map of pairs by users
     * @return list of found pairs
     */
    private List<PairDto> collectPairsByCommonInterests(final List<UserDto> users,
                                                        final Map<UserDto, Set<PairDto>> pairsByUser) {
        final List<UserDto> paired = new ArrayList<>();
        final List<PairDto> result = new ArrayList<>();
        users.forEach(user -> {
            if (paired.contains(user)) return;
            final Set<PairDto> pairs = pairsByUser.get(user);
            final PairDto pair = pairs.stream()
                    .filter(variant -> !paired.contains(variant.getPartner()))
                    .findFirst().orElse(null);
            if (Objects.nonNull(pair)) {
                result.add(pair);
                paired.add(pair.getSelf());
                paired.add(pair.getPartner());
                pairsByUser.remove(pair.getSelf());
                pairsByUser.remove(pair.getPartner());
            }
        });
        return result;
    }
}
