package tests.ailabs.jurassic2;

import aws.community.toolkits.bedrock.Models;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TestModel {
    @Test
    void shouldHaveTemperatureRangeAndDefault() {
        double defaultValue = Models.AILabsJurassic2.temperature().defaultValue();
        double min = Models.AILabsJurassic2.temperature().minimumValue();
        double max = Models.AILabsJurassic2.temperature().maximumValue();
        assertEquals(0.5, defaultValue);
        assertEquals(0, min);
        assertEquals(1, max);
    }

    @Test
    void shouldHaveTopPRangeAndDefault() {
        double defaultValue = Models.AILabsJurassic2.topP().defaultValue();
        double min = Models.AILabsJurassic2.topP().minimumValue();
        double max = Models.AILabsJurassic2.topP().maximumValue();
        assertEquals(0.5, defaultValue);
        assertEquals(0, min);
        assertEquals(1, max);
    }
}
