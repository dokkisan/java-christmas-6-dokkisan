package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class DecemberEventController {
    private final InputView inputView;
    private final OutputView outputView;

    public DecemberEventController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        int expectedVisitDate = getExpectedVisitDate();
    }

    private int getExpectedVisitDate() {
        while (true) {
            try {
                return inputView.askExpectedVisitDate();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
