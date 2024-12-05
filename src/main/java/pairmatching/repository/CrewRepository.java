package pairmatching.repository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pairmatching.domain.Crew;
import pairmatching.enums.Course;

public class CrewRepository implements Repository<Crew> {
    private final Map<Course, List<Crew>> crews = initialize();

    private Map<Course, List<Crew>> initialize() {
        return Arrays.stream(Course.values()).collect(Collectors.toMap(
                course -> course,
                course -> new ArrayList<>()
        ));
    }

    @Override
    public void add(Crew crew) {
        if (this.isExist(crew.getCourse(), crew.getName())) {
            throw new IllegalArgumentException("Crew already exists");
        }
        crews.get(crew.getCourse()).add(crew);
    }

    public Map<Course, List<Crew>> findAll() {
        return Map.copyOf(crews);
    }

    public boolean isExist(Course course, String name) {
        List<Crew> crewsByCourse = this.crews.get(course);
        return crewsByCourse.stream().anyMatch(crew -> crew.getName().equals(name));
    }
}
