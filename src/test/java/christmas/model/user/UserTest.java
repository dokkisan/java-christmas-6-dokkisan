package christmas.model.user;

import christmas.model.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UserTest {

    static IntStream pickRandomDaysInDecember() {
        return IntStream.rangeClosed(1, 31);
    }

    @DisplayName("예상 방문 일자를 범위에 맞게 입력 했을 경우 User 객체 생성")
    @ParameterizedTest
    @MethodSource("pickRandomDaysInDecember")
    void generateUserWithValidExpectedVisitDate(int dayOfDecember) {
        assertDoesNotThrow(() -> new User(dayOfDecember));
    }

    @DisplayName("예상 방문 일자를 범위에 맞지 않게 입력 했을 경우에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void validateExpectedVisitDateOutOfRange(int wrongExpectedVisitDate) {
        assertThatThrownBy(() -> new User(wrongExpectedVisitDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_DAYS_IN_DECEMBER.getMessage());
    }
}