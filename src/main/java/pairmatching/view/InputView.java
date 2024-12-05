package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.enums.GeneralCommand;

public final class InputView {

    private InputView() {
    }

    public static String getCommand() {
        System.out.println("기능을 선택하세요.");
        for (GeneralCommand command : GeneralCommand.values()) {
            System.out.printf("%s. %s", command.getInput(), command.getDescription());
            printNewLine();
        }
        return getUserInput();
    }

    public static String getPairMatchInput() {
        System.out.println("#############################################");
        System.out.println("과정: 백엔드 | 프론트엔드");
        System.out.println("미션:");
        System.out.println("  - 레벨1: 자동차경주 | 로또 | 숫자야구게임");
        System.out.println("  - 레벨2: 장바구니 | 결제 | 지하철노선도");
        System.out.println("  - 레벨3:");
        System.out.println("  - 레벨4: 성능개선 | 배포");
        System.out.println("  - 레벨5:");
        System.out.println("############################################");
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");
        return getUserInput();
    }

    public static String getRematch() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");
        return getUserInput();
    }

    private static void printNewLine() {
        System.out.println();
    }

    private static String getUserInput() {
        String userInput = Console.readLine();
        printNewLine();
        return userInput;
    }

}
