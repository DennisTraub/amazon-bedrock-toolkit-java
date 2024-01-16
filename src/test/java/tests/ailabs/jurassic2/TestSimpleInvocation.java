package tests.ailabs.jurassic2;

import aws.community.toolkits.bedrock.SimpleBedrockClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tests.TestBase;

import static aws.community.toolkits.bedrock.Models.AILABS_JURASSIC_2;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TestSimpleInvocation extends TestBase {

    @Test
    void withPrompt_ShouldReturnCompletion() {
        SimpleBedrockClient client = new SimpleBedrockClient();
        String prompt = "Hello";
        String completion = client.invokeModel(AILABS_JURASSIC_2, prompt);
        assertNotNullOrEmpty(completion);
        System.out.println(completion);
    }

    @Test
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
