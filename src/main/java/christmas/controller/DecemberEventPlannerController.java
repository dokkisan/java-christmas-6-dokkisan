package christmas.controller;

import christmas.OperationMessage;
import christmas.model.event.EventBenefitDetails;
import christmas.model.event.EventManager;
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
        Map<String, Integer> orderedMenuItems = getExpectedMenuItems();
        User user = new User(visitDate, orderedMenuItems);
        outputView.printOrderedMenuItems(orderedMenuItems);
        int totalOrderAmountBeforeDiscount = EventCalculator.calculateTotalOrderAmountBeforeDiscount(orderedMenuItems);
        outputView.print(totalOrderAmountBeforeDiscount);
        List<EventBenefitDetails> eventPlanBenefitResult = getEventBenefitDetails(totalOrderAmountBeforeDiscount, visitDate, orderedMenuItems);
        for (EventBenefitDetails details : eventPlanBenefitResult) {
            System.out.println(details.getName() + details.getBenefitAmount());
        }
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

    private List<EventBenefitDetails> getEventBenefitDetails(
            int totalOrderAmountBeforeDiscount, LocalDate visitDate, Map<String, Integer> orderedMenuItems) {
        EventManager eventManager = new EventManager();
        return eventManager.getEventPlanBenefitResult(totalOrderAmountBeforeDiscount, visitDate, orderedMenuItems);
    }
}
