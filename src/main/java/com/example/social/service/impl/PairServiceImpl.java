package com.example.social.service.impl;

import com.example.social.dto.PairDto;
import com.example.social.dto.PairEntryDto;
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
 * Implementation of pair lookup service.
 *
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
        final Set<PairEntryDto> pairsByUser = collectPairsByUser(users);
        return collectPairsByCommonInterests(pairsByUser);
    }

    /**
     * Helper method that collect list of pairs for each user.
     * Pairs sorted by number of common interests (used sorting in {@link TreeSet}).
     * Pair entries sorted by number of pairs for user.
     *
     * @param users list of users with their interests
     * @return mapped interests by user dto
     * @see java.util.TreeSet
     */
    private Set<PairEntryDto> collectPairsByUser(final List<UserDto> users) {
        // create map for fast access collections
        final Map<UserDto, Set<PairDto>> pairs = new HashMap<>();
        // initialize collections with tree sets
        users.forEach(user -> pairs.put(user, new TreeSet<>()));

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
                    pairs.get(first).add(new PairDto(first, second, size, common));
                    pairs.get(second).add(new PairDto(second, first, size, common));
                }
            }
        }
        // sort result map values based on comparator
        // at start will be placed users which have less pairs and pairs will be found for them at first
        return pairs.entrySet().stream().map(entry -> new PairEntryDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Helper method that iterate all users and looks for best matching pairs.
     *
     * @param pairEntries set of pairs by users
     * @return list of found pairs
     */
    private List<PairDto> collectPairsByCommonInterests(final Set<PairEntryDto> pairEntries) {
        final List<PairDto> result = new ArrayList<>();

        pairEntries.forEach(entry -> {
            if (entry.getUser().isExcluded()) return;
            final Set<PairDto> pairs = entry.getPairs();
            final PairDto pair = pairs.stream().filter(PairDto::isPartnerNotExcluded).findFirst().orElse(null);
            if (Objects.nonNull(pair)) {
                result.add(pair);
                entry.getUser().setExcluded(true);
                pair.getPartner().setExcluded(true);
            }
        });

        return result;
    }
}
