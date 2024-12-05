package pairmatching.dtos;

import java.util.List;

public record MatchingResult(List<MatchingSingleResult> results) {
    public record MatchingSingleResult(List<String> names) {

    }
}
