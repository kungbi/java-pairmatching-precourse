package pairmatching.repository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import pairmatching.domain.CrewMember;
import pairmatching.enums.Course;

public class CrewRepository implements Repository<CrewMember> {
    private final Map<Course, List<CrewMember>> crews = initialize();

    private Map<Course, List<CrewMember>> initialize() {
        return Arrays.stream(Course.values()).collect(Collectors.toMap(
                course -> course,
                course -> new ArrayList<>()
        ));
    }

    @Override
    public void add(CrewMember crew) {
        if (this.isExist(crew.getCourse(), crew.getName())) {
            throw new IllegalArgumentException("Crew already exists");
        }
        crews.get(crew.getCourse()).add(crew);
    }

    public Map<Course, List<CrewMember>> findAll() {
        return Map.copyOf(crews);
    }

    public boolean isExist(Course course, String name) {
        List<CrewMember> crewsByCourse = this.crews.get(course);
        return crewsByCourse.stream().anyMatch(crew -> crew.getName().equals(name));
    }

    public List<CrewMember> findAllByCourse(Course course) {
        return List.copyOf(this.crews.get(course));
    }

    public Optional<CrewMember> findByCourseAndName(Course course, String name) {
        List<CrewMember> crewsByCourse = this.crews.get(course);
        return crewsByCourse.stream().filter(crew -> crew.getName().equals(name)).findFirst();
    }
}
