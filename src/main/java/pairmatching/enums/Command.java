package pairmatching.enums;

public enum Command {
    MAKE_PAIR("1", "페어 매칭"),
    RETRIEVE_PAIR("2", "페어 조회"),
    RESET_PAIR("3", "페어 초기화"),
    QUIT("Q", "종료");

    private final String command;
    private final String description;

    Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
