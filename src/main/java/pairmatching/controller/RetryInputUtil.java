package pairmatching.controller;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import pairmatching.dtos.PairMatchInputDto;
import pairmatching.enums.GeneralCommand;
import pairmatching.enums.RematchCommand;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public final class RetryInputUtil {

    private RetryInputUtil() {
    }

    public static GeneralCommand getCommand() {
        return retryLogics(InputView::getCommand, GeneralCommand::findByCommand);
    }

    public static RematchCommand getRematch() {
        return retryLogics(InputView::getRematch, RematchCommand::findByInput);
    }

    public static PairMatchInputDto getPairMatchInput() {
        return retryLogics(InputView::getPairMatchInput, InputParser::parsePairMatchInputDto);
    }

    private static <T> T retryLogics(Supplier<String> userInputReader, Function<String, T> parser) {
        while (true) {
            try {
                String userInput = userInputReader.get();
                return parser.apply(userInput);
            } catch (IllegalArgumentException error) {
                OutputView.printError(error.getMessage());
            }
        }
    }
}
