package pairmatching.domain;

import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomCrewPairGeneratorTest {
    List<String> returns_even = List.of("철수", "영희", "맹구", "짱구");
    List<String> returns_odd = List.of("철수", "영희", "맹구", "짱구", "짱아");

    static ShuffleUtil getShuffleUtil(List<String> crew) {
        return new ShuffleUtil() {
            @Override
            public List<String> shuffle(List<String> items) {
                return crew;
            }
        };
    }

    @Test
    void 그룹_매칭_테스트_짝수() {
        RandomCrewPairGenerator randomCrewGenerator = new RandomCrewPairGenerator(getShuffleUtil(returns_even), returns_even);

        Assertions.assertThat(randomCrewGenerator.pollNextGroup()).isEqualTo(List.of("철수", "영희"));
        randomCrewGenerator.pop();

        Assertions.assertThat(randomCrewGenerator.pollNextGroup()).isEqualTo(List.of("맹구", "짱구"));
        randomCrewGenerator.pop();

        Assertions.assertThat(randomCrewGenerator.getSize()).isEqualTo(0);
    }

    @Test
    void 그룹_매칭_테스트_홀수() {
        RandomCrewPairGenerator randomCrewGenerator = new RandomCrewPairGenerator(getShuffleUtil(returns_odd), returns_odd);

        Assertions.assertThat(randomCrewGenerator.pollNextGroup()).isEqualTo(List.of("철수", "영희"));
        randomCrewGenerator.pop();

        Assertions.assertThat(randomCrewGenerator.pollNextGroup()).isEqualTo(List.of("맹구", "짱구", "짱아"));
        randomCrewGenerator.pop();

        Assertions.assertThat(randomCrewGenerator.getSize()).isEqualTo(0);
    }

}