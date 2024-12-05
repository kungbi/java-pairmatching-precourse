package pairmatching.view;

import java.util.List;
import org.junit.jupiter.api.Test;
import pairmatching.dtos.MatchingResultDto;
import pairmatching.dtos.MatchingResultDto.MatchingSingleResult;

class OutputViewTest {

    @Test
    void 매칭_결과_테스트() {
        MatchingResultDto matchingResult = new MatchingResultDto(List.of(
                new MatchingSingleResult(List.of("철수", "맹구")),
                new MatchingSingleResult(List.of("짱구", "짱아", "유리"))
        ));

        OutputView.printMatchingResult(matchingResult);
    }

}