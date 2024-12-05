package pairmatching.view;

import java.util.List;
import pairmatching.dtos.MatchingResultDto;
import pairmatching.dtos.MatchingResultDto.MatchingSingleResult;

public final class OutputView {

    private OutputView() {

    }

    public static void printMatchingResult(MatchingResultDto matchingResult) {
        List<MatchingSingleResult> results = matchingResult.results();
        System.out.println("페어 매칭 결과입니다.");
        results.forEach(
                result -> {
                    System.out.print(getFormattedNames(result.names()));
                    printNewLine();
                }
        );
    }

    public static void printResetMessage() {
        System.out.println("초기화 되었습니다.");
        printNewLine();
    }

    public static void printError(String message) {
        System.out.println("[ERROR] " + message);
        printNewLine();
    }

    private static String getFormattedNames(List<String> names) {
        String result = "";
        for (int i = 0; i < names.size(); i++) {
            result += names.get(i);
            if (i != names.size() - 1) {
                result += " : ";
            }
        }
        return result;
    }

    private static void printNewLine() {
        System.out.println();
    }
}
