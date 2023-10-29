package racingcar.domain.round;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoundDtoValidatorTest {

    @Test
    @DisplayName("숫자가 아닌 문자는 입력하실 수 없습니다.")
    void exceptNotNumber() {
        // GIVEN
        RoundDto onlyString = new RoundDto("nin");
        RoundDto prefixNumberStr = new RoundDto("1nin");
        RoundDto suffixNumberStr = new RoundDto("nin1");
        RoundDto middleNumberStr = new RoundDto("ni11n");

        // WHEN
        IllegalArgumentException onlyStringThrown = assertThrows(IllegalArgumentException.class, () -> {
            RoundDtoValidator.validateNotNumber.apply(onlyString.roundInput());
        });
        IllegalArgumentException prefixNumberStrThrown = assertThrows(IllegalArgumentException.class, () -> {
            RoundDtoValidator.validateNotNumber.apply(prefixNumberStr.roundInput());
        });
        IllegalArgumentException suffixNumberStrThrown = assertThrows(IllegalArgumentException.class, () -> {
            RoundDtoValidator.validateNotNumber.apply(suffixNumberStr.roundInput());
        });
        IllegalArgumentException middleNumberStrThrown = assertThrows(IllegalArgumentException.class, () -> {
            RoundDtoValidator.validateNotNumber.apply(middleNumberStr.roundInput());
        });

        // THEN
        assertEquals(onlyStringThrown.getMessage(), RoundDtoValidator.NOT_NUMBER_EXCEPTION_MESSAGE);
        assertEquals(prefixNumberStrThrown.getMessage(), RoundDtoValidator.NOT_NUMBER_EXCEPTION_MESSAGE);
        assertEquals(suffixNumberStrThrown.getMessage(), RoundDtoValidator.NOT_NUMBER_EXCEPTION_MESSAGE);
        assertEquals(middleNumberStrThrown.getMessage(), RoundDtoValidator.NOT_NUMBER_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("0이거나 음수인 문자는 입력하실 수 없습니다.")
    void exceptZeroOrNegativeNumber() {
        // GIVEN
        RoundDto zeroCase = new RoundDto("0");
        RoundDto negativeCase = new RoundDto("-123");

        // WHEN
        IllegalArgumentException zeroCaseThrown = assertThrows(IllegalArgumentException.class, () -> {
            RoundDtoValidator.validateNotPositiveNumber.apply(zeroCase.roundInput());
        });
        IllegalArgumentException negativeCaseThrown = assertThrows(IllegalArgumentException.class, () -> {
            RoundDtoValidator.validateNotPositiveNumber.apply(negativeCase.roundInput());
        });

        // THEN
        assertEquals(zeroCaseThrown.getMessage(), RoundDtoValidator.NOT_POSITIVE_NUMBER_MESSAGE);
        assertEquals(negativeCaseThrown.getMessage(), RoundDtoValidator.NOT_POSITIVE_NUMBER_MESSAGE);
    }

    @Test
    @DisplayName("정수가 아닌 숫자는 입력하실 수 없습니다.")
    void exceptNotInteger() {
        // GIVEN
        RoundDto floatingNumberCase = new RoundDto("123.1");
        RoundDto positiveFloatingNumberCase = new RoundDto("+123.1");
        RoundDto negativeFloatingNumberCase = new RoundDto("-123.1");

        // WHEN
        IllegalArgumentException floatingNumberCaseThrown = assertThrows(IllegalArgumentException.class, () -> {
            RoundDtoValidator.validateNotInteger.apply(floatingNumberCase.roundInput());
        });
        IllegalArgumentException positiveFloatingNumberCaseThrown = assertThrows(IllegalArgumentException.class, () -> {
            RoundDtoValidator.validateNotInteger.apply(positiveFloatingNumberCase.roundInput());
        });
        IllegalArgumentException negativeFloatingNumberCaseThrown = assertThrows(IllegalArgumentException.class, () -> {
            RoundDtoValidator.validateNotInteger.apply(negativeFloatingNumberCase.roundInput());
        });

        // THEN
        assertEquals(floatingNumberCaseThrown.getMessage(), RoundDtoValidator.NOT_INTEGER_EXCEPTION_MESSAGE);
        assertEquals(positiveFloatingNumberCaseThrown.getMessage(), RoundDtoValidator.NOT_INTEGER_EXCEPTION_MESSAGE);
        assertEquals(negativeFloatingNumberCaseThrown.getMessage(), RoundDtoValidator.NOT_INTEGER_EXCEPTION_MESSAGE);
    }
}