package pairmatching.enums;

public enum Mission {
    RACE("자동차 경주", Level.LEVEL_1),
    LOTTERY("로또", Level.LEVEL_1),
    NUMBER_BASEBALL_GAME("숫자야구게임", Level.LEVEL_1),

    SHOPPING_CART("장바구니", Level.LEVEL_2),
    PAYMENT("결제", Level.LEVEL_2),
    SUBWAY_MAP("지하철노선도", Level.LEVEL_2),

    PERFORMANCE_IMPROVEMENT("성능개선", Level.LEVEL_4),
    DEPLOYMENT("배포", Level.LEVEL_4);

    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }
}
