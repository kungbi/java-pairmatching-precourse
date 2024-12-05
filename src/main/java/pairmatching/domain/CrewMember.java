package pairmatching.domain;

import java.util.Objects;
import pairmatching.enums.Course;

public class CrewMember {
    private final String name;
    private final Course course;

    public CrewMember(String name, Course course) {
        if (!(1 <= name.length() && name.length() <= 10)) {
            throw new IllegalArgumentException("Name must be between 1 and 10 characters");
        }
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Crew{" +
               "name='" + name + '\'' +
               ", course=" + course +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CrewMember crew = (CrewMember) o;
        return Objects.equals(name, crew.name) && course == crew.course;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, course);
    }
}
