package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomShuffleUtil implements ShuffleUtil {
    @Override
    public List<String> shuffle(List<String> items) {
        return Randoms.shuffle(items);
    }
}
