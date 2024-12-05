package pairmatching.enums;

public enum GeneralCommand {
    MAKE_PAIR("1", "페어 매칭"),
    RETRIEVE_PAIR("2", "페어 조회"),
    RESET_PAIR("3", "페어 초기화"),
    QUIT("Q", "종료");

    private final String input;
    private final String description;

    GeneralCommand(String command, String description) {
        this.input = command;
        this.description = description;
    }

    public static GeneralCommand findByCommand(String value) {
        for (GeneralCommand command : GeneralCommand.values()) {
            if (command.input.equals(value)) {
                return command;
            }
        }
        throw new IllegalArgumentException("없는 명령 입니다");
    }

    public String getInput() {
        return input;
    }

    public String getDescription() {
        return description;
    }
}
