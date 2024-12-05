package pairmatching.dtos;

import pairmatching.enums.Course;
import pairmatching.enums.Level;
import pairmatching.enums.Mission;

public record PairMatchInputDto(Course course, Level level, Mission mission) {
}
