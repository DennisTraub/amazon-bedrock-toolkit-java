package tests.ailabs.jurassic2;

import aws.community.toolkits.bedrock.SimpleBedrockClient;
import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Request;
import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;
import tests.TestBase;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TestInvocationWithRequestBuilder extends TestBase {

    @Test
    @Tag("UnitTest")
    void shouldAcceptPrompt() {
        Jurassic2Request request = Jurassic2Request.builder().prompt("Hello").build();
        assertNotNull(request);
    }

    @Test
    @Tag("UnitTest")
    void invalidPrompt_ShouldThrow() {
        String nullPrompt = null;
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> Jurassic2Request.builder().prompt(nullPrompt).build()
        );

        String emptyPrompt = "";
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> Jurassic2Request.builder().prompt(emptyPrompt).build()
        );

        String blankPrompt = "    ";
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> Jurassic2Request.builder().prompt(blankPrompt).build()
        );
    }

    @Test
    @Tag("IntegrationTest")
    void shouldReturnHighLevelObjects() {
        SimpleBedrockClient client = new SimpleBedrockClient();

        Jurassic2Request request = Jurassic2Request.builder()
                .prompt("Hello")
                .temperature(0.5)
                .build();

        Jurassic2Response response = client.invokeModel(request);

        String completion = response.completion();

        assertNotNullOrEmpty(completion);
    }

    @Test
    void shouldReturnLowLevelSdkResponse() {
        SimpleBedrockClient client = new SimpleBedrockClient();

        Jurassic2Request request = Jurassic2Request.builder()
                .prompt("Hello")
                .build();

        Jurassic2Response response = client.invokeModel(request);

        InvokeModelResponse sdkResponse = response.sdkResponse();

        assertNotNull(sdkResponse);
        System.out.println(sdkResponse);
    }
}