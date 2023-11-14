package christmas.controller;

import christmas.OperationMessage;
import christmas.model.menu.MenuManager;
import christmas.model.user.User;
import christmas.util.DateConverter;
import christmas.util.EventCalculator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DecemberEventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;

    public DecemberEventPlannerController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.print(OperationMessage.GREETING_AND_INTRODUCE.getMessage());
        LocalDate visitDate = getExpectedVisitDate();
        Map<String, Integer> menuItems = getExpectedMenuItems();
        User user = new User(visitDate, menuItems);
        outputView.printOrderedMenuItems(menuItems);
        outputView.print(EventCalculator.calculateTotalOrderAmountBeforeDiscount(menuItems));
    }

    private LocalDate getExpectedVisitDate() {
        outputView.print(OperationMessage.INPUT_EXPECTED_VISIT_DATE.getMessage());
        while (true) {
            try {
                int visitDate = inputView.askExpectedVisitDate();
                return DateConverter.convertToLocalDate(visitDate);
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    private Map<String, Integer> getExpectedMenuItems() {
        while (true) {
            try {
                outputView.print(OperationMessage.INPUT_EXPECTED_MENU_ITEMS.getMessage());
                List<String> menuItems = inputView.askExpectedMenuItems();
                return MenuManager.validate(menuItems);
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }
}
