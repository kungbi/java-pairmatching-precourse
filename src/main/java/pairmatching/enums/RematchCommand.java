package pairmatching.enums;

public enum RematchCommand {
    YES("네"),
    NO("아니오");

    private final String input;

    RematchCommand(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public static RematchCommand findByInput(String input) {
        for (RematchCommand command : RematchCommand.values()) {
            if (command.input.equals(input)) {
                return command;
            }
        }
        throw new IllegalArgumentException("네 아니오 중 입력해주세요");
    }
}
