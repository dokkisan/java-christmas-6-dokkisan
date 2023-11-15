package christmas.controller;

import christmas.model.message.OperationMessage;
import christmas.model.DecemberEventBadge;
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

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;

    public EventController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.printTotalOrderAmountBeforeDiscount(OperationMessage.GREETING_AND_INTRODUCE.getMessage());
        User user = new User(getExpectedVisitDate(), getExpectedMenuItems());
        outputView.printOrderedMenuItems(user.getOrderedMenuItems());
        int totalOrderAmountBeforeDiscount = EventCalculator.calculateTotalOrderAmountBeforeDiscount(user.getOrderedMenuItems());
        outputView.printTotalOrderAmountBeforeDiscount(totalOrderAmountBeforeDiscount);
        List<EventBenefitDetails> eventPlanBenefitResult = getEventBenefitDetails(totalOrderAmountBeforeDiscount, user.getVisitDate(), user.getOrderedMenuItems());
        int totalBenefitAmount = EventCalculator.calculateTotalBenefitAmount(eventPlanBenefitResult);
        printEventBenefitDetails(totalOrderAmountBeforeDiscount, totalBenefitAmount, eventPlanBenefitResult);
    }

    private void printEventBenefitDetails(
            int totalOrderAmountBeforeDiscount, int totalBenefitAmount, List<EventBenefitDetails> eventPlanBenefitResult) {
        EventManager eventManager = new EventManager();
        outputView.printChampagneGifted(eventManager.getChampagneGiftedCount());
        outputView.printTotalBenefitsAmount(totalBenefitAmount);
        outputView.printBenefitsDetails(eventPlanBenefitResult);
        outputView.printExpectedPaymentAfterDiscount(eventManager.getExpectedPaymentAfterDiscount(totalOrderAmountBeforeDiscount));
        printAssignedDecemberEventBadge();
    }

    private void printAssignedDecemberEventBadge() {
        EventManager eventManager = new EventManager();
        eventManager.createEventBadge();
        for (DecemberEventBadge badge : DecemberEventBadge.values()) {
            if (eventManager.isAssignedBadge(badge)) {
                outputView.printAssignedBadge(badge);
            }
        }
    }

    private LocalDate getExpectedVisitDate() {
        outputView.printTotalOrderAmountBeforeDiscount(OperationMessage.INPUT_EXPECTED_VISIT_DATE.getMessage());
        while (true) {
            try {
                int visitDate = inputView.askExpectedVisitDate();
                return DateConverter.convertToLocalDate(visitDate);
            } catch (IllegalArgumentException e) {
                outputView.printTotalOrderAmountBeforeDiscount(e.getMessage());
            }
        }
    }

    private Map<String, Integer> getExpectedMenuItems() {
        while (true) {
            try {
                outputView.printTotalOrderAmountBeforeDiscount(OperationMessage.INPUT_EXPECTED_MENU_ITEMS.getMessage());
                List<String> menuItems = inputView.askExpectedMenuItems();
                return MenuManager.validate(menuItems);
            } catch (IllegalArgumentException e) {
                outputView.printTotalOrderAmountBeforeDiscount(e.getMessage());
            }
        }
    }

    private List<EventBenefitDetails> getEventBenefitDetails(
            int totalOrderAmountBeforeDiscount, LocalDate visitDate, Map<String, Integer> orderedMenuItems) {
        EventManager eventManager = new EventManager();
        return eventManager.getEventPlanBenefitResult(totalOrderAmountBeforeDiscount, visitDate, orderedMenuItems);
    }
}
