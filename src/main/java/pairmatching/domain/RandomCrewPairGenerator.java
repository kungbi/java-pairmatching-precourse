package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class RandomCrewPairGenerator {
    private final ShuffleUtil shuffleUtil;
    private List<String> crewMembers;


    public RandomCrewPairGenerator(ShuffleUtil shuffleUtil, List<String> crewMembers) {
        this.shuffleUtil = shuffleUtil;
        this.crewMembers = new ArrayList<>(shuffleUtil.shuffle(crewMembers));
    }

    public List<String> pollNextGroup() {
        if (this.getSize() == 3) {
            return this.getThreeMembers();
        }
        if (2 <= this.getSize()) {
            return this.getTwoMembers();
        }

        throw new IllegalStateException();
    }

    public void pop() {
        if (this.getSize() == 3) {
            popCrews(3);
            return;
        }
        if (2 <= this.getSize()) {
            popCrews(2);
            return;
        }
        throw new IllegalStateException();
    }

    private void popCrews(int count) {
        for (int i = 0; i < count; i++) {
            this.crewMembers.removeFirst();
        }
    }

    public void shuffle() {
        this.crewMembers = shuffleUtil.shuffle(this.crewMembers);
    }

    public int getSize() {
        return crewMembers.size();
    }


    private List<String> getTwoMembers() {
        if (this.getSize() < 2) {
            throw new IllegalStateException();
        }
        return List.of(crewMembers.get(0), crewMembers.get(1));
    }


    private List<String> getThreeMembers() {
        if (this.getSize() < 3) {
            throw new IllegalStateException();
        }
        return List.of(crewMembers.get(0), crewMembers.get(1), crewMembers.get(2));
    }
}
