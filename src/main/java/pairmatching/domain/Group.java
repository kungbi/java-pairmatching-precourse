package pairmatching.domain;

import java.util.Objects;
import java.util.Set;

public class Group {
    private final Set<CrewMember> crews;

    public Group(Set<CrewMember> crews) {
        this.crews = crews;
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
