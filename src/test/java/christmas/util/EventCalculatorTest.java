package christmas.util;

import christmas.model.DecemberEventBadge;
import christmas.model.event.DecemberEvents;
import christmas.model.event.EventBenefitDetails;
import christmas.model.menu.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class EventCalculatorTest {
    Map<String, Integer> orderedMenuItem = new HashMap<>();
    List<EventBenefitDetails> eventPlanBenefitResult = new ArrayList<>();

    @BeforeEach
    void setUp() {
        orderedMenuItem.put(MenuItem.CHOCOLATE_CAKE.getName(), 1);
        orderedMenuItem.put(MenuItem.ZERO_COKE.getName(), 1);
        for (DecemberEvents events : DecemberEvents.values()) {
            if (events.getName().equals(DecemberEvents.WEEKDAY_DISCOUNT.getName())) {
                eventPlanBenefitResult.add(new EventBenefitDetails(events.getName(), 2_023));
                return;
            }
            eventPlanBenefitResult.add(new EventBenefitDetails(events.getName(), 0));
        }
    }

    @DisplayName("총혜택 금액에 따른 배지를 판단")
    @ParameterizedTest
    @ValueSource(ints = {4_999, 9_999, 19_999, 20_000})
    void calculateEventBadge(int totalBenefitAmount) {
        if (totalBenefitAmount <= 4_999) {
            assertThat(EventCalculator
                    .calculateEventBadge(totalBenefitAmount))
                    .isEqualTo(DecemberEventBadge.NONE);
            return;
        }
        if (totalBenefitAmount <= 9_999) {
            assertThat(EventCalculator
                    .calculateEventBadge(totalBenefitAmount))
                    .isEqualTo(DecemberEventBadge.STAR);
            return;
        }
        if (totalBenefitAmount <= 19_999) {
            assertThat(EventCalculator
                    .calculateEventBadge(totalBenefitAmount))
                    .isEqualTo(DecemberEventBadge.TREE);
            return;
        }
        assertThat(EventCalculator
                .calculateEventBadge(totalBenefitAmount))
                .isEqualTo(DecemberEventBadge.SANTA);
    }

    @DisplayName("할인 전 총주문 금액 계산")
    @Test
    void calculateTotalOrderAmountBeforeDiscount() {
        assertThat(EventCalculator.calculateTotalOrderAmountBeforeDiscount(orderedMenuItem))
                .isEqualTo(MenuItem.CHOCOLATE_CAKE.getPrice() + MenuItem.ZERO_COKE.getPrice());
    }

    @DisplayName("이벤트 혜택 금액 계산")
    @Test
    void calculateTotalBenefitAmount() {
        assertThat(EventCalculator.calculateTotalBenefitAmount(eventPlanBenefitResult))
                .isEqualTo(2_023);
    }

    @DisplayName("할인 후 예상 결제 금액 계산")
    @Test
    void calculateExpectedPaymentAfterDiscount() {
        int totalOrderAmountBeforeDiscount = MenuItem.CHOCOLATE_CAKE.getPrice() + MenuItem.ZERO_COKE.getPrice();
        assertThat(EventCalculator
                .calculateExpectedPaymentAfterDiscount(totalOrderAmountBeforeDiscount, eventPlanBenefitResult))
                .isEqualTo(MenuItem.CHOCOLATE_CAKE.getPrice() + MenuItem.ZERO_COKE.getPrice() - 2_023);
    }
}