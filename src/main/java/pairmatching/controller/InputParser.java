package pairmatching.controller;

import java.util.Arrays;
import java.util.List;
import pairmatching.dtos.PairMatchInputDto;
import pairmatching.enums.Course;
import pairmatching.enums.Level;
import pairmatching.enums.Mission;

public class InputParser {

    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static PairMatchInputDto parsePairMatchInputDto(String input) {
        List<String> splitInput = Arrays.stream(input.split(",")).toList();
        if (splitInput.size() != 3) {
            throw new IllegalArgumentException("과정, 레벨, 미션을 선택하세요.");
        }

        Course course = Course.findByName(splitInput.get(0).trim());
        Level level = Level.findByName(splitInput.get(1).trim());
        Mission mission = Mission.findByName(splitInput.get(2).trim());
        return new PairMatchInputDto(course, level, mission);
    }

}
