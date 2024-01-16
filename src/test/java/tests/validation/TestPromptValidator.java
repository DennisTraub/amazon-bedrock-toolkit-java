package tests.validation;

import aws.community.toolkits.bedrock.tools.validation.PromptValidator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TestPromptValidator {

    @Test
    @Tag("UnitTest")
    void validateShouldAllowPrompt() {
        String prompt = "A test prompt";
        assertDoesNotThrow(() -> PromptValidator.validate(prompt));
    }

    @Test
    @Tag("UnitTest")
    void validateShouldThrowOnNull() {
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> PromptValidator.validate(null)
        );
    }

    @Test
    @Tag("UnitTest")
    void validateShouldThrowOnEmpty() {
        String empty = "";
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> PromptValidator.validate(empty)
        );
    }

    @Test
    @Tag("UnitTest")
    void validateShouldThrowOnBlank() {
        String blank = "   ";
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> PromptValidator.validate(blank)
        );
    }
}
