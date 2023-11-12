package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.ErrorMessage;

public class InputView {

    public int askExpectedVisitDate() {
        return parseInt(Console.readLine());
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_FORMAT.getMessage());
        }
    }
}
