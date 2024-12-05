package pairmatching.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.Crew;
import pairmatching.enums.Course;

public class CrewRepository implements Repository<Crew> {
    private final Map<Course, List<Crew>> crews = new HashMap<>(
            Map.of(Course.BACKEND, new ArrayList<>(), Course.FRONTEND, new ArrayList<>()));

    @Override
    public void add(Crew crew) {
        crews.get(crew.getCourse()).add(crew);
    }

    public Map<Course, List<Crew>> findAll() {
        return Map.copyOf(crews);
    }

}
