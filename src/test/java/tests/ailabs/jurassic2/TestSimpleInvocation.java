package tests.ailabs.jurassic2;

import aws.community.toolkits.bedrock.SimpleBedrockClient;
import aws.community.toolkits.bedrock.common.TextGenerationConfig;
import org.junit.jupiter.api.*;
import tests.common.TestBase;

import static aws.community.toolkits.bedrock.Models.AILABS_JURASSIC_2;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TestSimpleInvocation extends TestBase {

    static SimpleBedrockClient client;
    static String TEST_PROMPT = "Hello";

    @BeforeAll
    static void setup() {
        client = new SimpleBedrockClient();
    }

    @Test
    @Tag("IntegrationTest")
    void withPrompt_ShouldReturnCompletion() {
        String completion = client.invokeModel(AILABS_JURASSIC_2, TEST_PROMPT);
        assertNotNullOrEmpty(completion);
        System.out.println(completion);
    }

    @Test
    @Tag("IntegrationTest")
    void withDefaultParameterObject_ShouldReturnCompletion() {
        String completion = client.invokeModel(AILABS_JURASSIC_2, TEST_PROMPT, TextGenerationConfig.DEFAULT);
        assertNotNullOrEmpty(completion);
        System.out.println(completion);
    }

    @Test
    @Tag("IntegrationTest")
    void temperatureRange_shouldBeBetweenZeroAndOne() {
        TextGenerationConfig zero = new TextGenerationConfig(0);
        client.invokeModel(AILABS_JURASSIC_2, TEST_PROMPT, zero);

        TextGenerationConfig one = new TextGenerationConfig(1);
        client.invokeModel(AILABS_JURASSIC_2, TEST_PROMPT, one);

        assertTrue(true);
    }

    @Test
    @Tag("UnitTest")
    void invalidTemperature_ShouldThrow() {
        TextGenerationConfig tooLow = new TextGenerationConfig(-0.1);

        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> client.invokeModel(AILABS_JURASSIC_2, TEST_PROMPT, tooLow)
        );

        TextGenerationConfig tooHigh = new TextGenerationConfig(1.1);
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> client.invokeModel(AILABS_JURASSIC_2, TEST_PROMPT, tooHigh)
        );
    }

    @Test
    @Tag("UnitTest")
    void withInvalidPrompt_ShouldThrow() {
        SimpleBedrockClient client = new SimpleBedrockClient();

        String nullPrompt = null;
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> client.invokeModel(AILABS_JURASSIC_2, nullPrompt)
        );

        String emptyPrompt = "";
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> client.invokeModel(AILABS_JURASSIC_2, emptyPrompt)
        );

        String blankPrompt = "    ";
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> client.invokeModel(AILABS_JURASSIC_2, blankPrompt)
        );
    }
}
