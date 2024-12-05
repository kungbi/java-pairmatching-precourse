package pairmatching.domain;

import java.util.List;
import pairmatching.enums.Course;
import pairmatching.enums.Mission;

public class MatchGroups {
    private final List<Group> matches;
    private final Mission mission;
    private final Course course;

    public MatchGroups(List<Group> matches, Mission mission, Course course) {
        this.matches = matches;
        this.mission = mission;
        this.course = course;
    }

    public List<Group> getGroups() {
        return matches;
    }

    public Mission getMission() {
        return mission;
    }

    public Course getCourse() {
        return course;
    }

    public boolean containGroup(Group group) {
        return matches.contains(group);
    }

    @Override
    public String toString() {
        return "MatchGroups{" +
               "matches=" + matches +
               ", mission=" + mission +
               ", course=" + course +
               '}';
    }
}
