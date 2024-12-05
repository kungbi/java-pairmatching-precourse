package pairmatching.dtos;

import java.util.List;
import pairmatching.domain.MatchGroups;

public record MatchingResultDto(List<MatchingSingleResult> results) {
    public static MatchingResultDto from(MatchGroups groups) {
        return new MatchingResultDto(
                groups.getGroups().stream().map(group -> new MatchingSingleResult(group.getNames())).toList()
        );
    }

    public record MatchingSingleResult(List<String> names) {

    }
}
