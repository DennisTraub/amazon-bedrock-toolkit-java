package tests.common;

import aws.community.toolkits.bedrock.tools.validation.ModelConfigValidator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TestModelConfigValidator {
    @Test
    @Tag("UnitTest")
    void validateTemperatureShouldAllowValidValues() {
        double min = 0;
        double max = 1;
        assertDoesNotThrow(() -> ModelConfigValidator.validateTemperature(min, min, max));
        assertDoesNotThrow(() -> ModelConfigValidator.validateTemperature(max/2, min, max));
        assertDoesNotThrow(() -> ModelConfigValidator.validateTemperature(max, min, max));
    }

    @Test
    @Tag("UnitTest")
    void validateTemperatureShouldThrowOnInvalidValues() {
        double min = 0;
        double max = 1;
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> ModelConfigValidator.validateTemperature(min - 0.1, min, max)
        );
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> ModelConfigValidator.validateTemperature(max + 0.1, min, max)
        );
    }
}
