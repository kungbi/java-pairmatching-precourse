package pairmatching.repository;

import java.util.HashMap;
import java.util.Map;
import pairmatching.domain.Group;
import pairmatching.domain.MatchGroups;
import pairmatching.enums.Course;
import pairmatching.enums.Level;
import pairmatching.enums.Mission;

public class MatchRepository implements Repository<MatchGroups> {
    private final Map<Course, Map<Mission, MatchGroups>> matches = new HashMap<>();

    @Override
    public void add(MatchGroups matchGroups) {
        this.matches.computeIfAbsent(matchGroups.getCourse(), k -> new HashMap<>());
        if (this.isExist(matchGroups.getCourse(), matchGroups.getMission())) {
            throw new IllegalStateException("Match groups already exists");
        }
        this.matches.get(matchGroups.getCourse()).put(matchGroups.getMission(), matchGroups);
    }

    public boolean isExist(Course course, Mission mission) {
        if (!this.matches.containsKey(course)) {
            return false;
        }
        Map<Mission, MatchGroups> missionMatchGroups = this.matches.get(course);
        if (!missionMatchGroups.containsKey(mission)) {
            return false;
        }
        return true;
    }

    public boolean hasSameGroupInLevel(Course course, Level level, Group group) {
        Map<Mission, MatchGroups> missionMatchGroups = this.matches.get(course);
        if (missionMatchGroups == null) {
            return false;
        }

        for (Mission mission : missionMatchGroups.keySet()) {
            if (mission.getLevel() != level) {
                continue;
            }

            MatchGroups matchGroups = missionMatchGroups.get(mission);
            if (matchGroups.containGroup(group)) {
                return true;
            }
        }
        return false;
    }

}
