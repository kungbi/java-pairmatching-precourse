package pairmatching.enums;

public enum Level {
    LEVEL_1("레벨1"),
    LEVEL_2("레벨2"),
    LEVEL_3("레벨3"),
    LEVEL_4("레벨4"),
    LEVEL_5("레벨5");

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public static Level findByName(String name) {
        for (Level level : Level.values()) {
            if (level.name.equals(name)) {
                return level;
            }
        }
        throw new IllegalArgumentException("없는 레벨 입니다");
    }
}
