package tests.ailabs.jurassic2;

import aws.community.toolkits.bedrock.SimpleBedrockClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tests.testutilities.TestBase;

import static aws.community.toolkits.bedrock.Models.AILabsJurassic2;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

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
        String completion = client.invokeModel(AILabsJurassic2, TEST_PROMPT);
        assertNotNullOrEmpty(completion);
    }

    @Test
    @Tag("UnitTest")
    void withInvalidPrompt_ShouldThrow() {
        String nullPrompt = null;
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> client.invokeModel(AILabsJurassic2, nullPrompt)
        );

        String emptyPrompt = "";
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> client.invokeModel(AILabsJurassic2, emptyPrompt)
        );

        String blankPrompt = "    ";
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> client.invokeModel(AILabsJurassic2, blankPrompt)
        );
    }
}
