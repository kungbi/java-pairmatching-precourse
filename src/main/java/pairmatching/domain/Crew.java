package pairmatching.domain;

import pairmatching.enums.Course;
import pairmatching.enums.Level;

public class Crew {
    private final String name;
    private final Course course;

    public Crew(String name, Course course) {
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
}
