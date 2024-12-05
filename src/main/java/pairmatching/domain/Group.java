package pairmatching.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Group {
    private final List<CrewMember> crews;

    public Group(List<CrewMember> crews) {
        this.crews = crews;
    }

    public List<String> getNames() {
        return crews.stream().map(CrewMember::getName).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(crews, group.crews);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(crews);
    }

    @Override
    public String toString() {
        return "Group{" +
               "crews=" + crews +
               '}';
    }
}
