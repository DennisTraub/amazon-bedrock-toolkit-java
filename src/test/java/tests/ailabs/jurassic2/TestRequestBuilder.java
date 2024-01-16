package tests.ailabs.jurassic2;

import aws.community.toolkits.bedrock.SimpleBedrockClient;
import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Request;
import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;
import tests.common.TestBase;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TestRequestBuilder extends TestBase {

    @Test
    @Tag("UnitTest")
    void shouldAcceptPrompt() {
        Jurassic2Request request = Jurassic2Request.builder().prompt("Hello").build();
        assertNotNull(request);
    }

    @Test
    @Tag("UnitTest")
    void defaultTemperatureShouldBeZeroPointFive() {
        Jurassic2Request request = Jurassic2Request.builder().prompt("Hello").build();
        assertEquals(0.5, getTemperature(request));
    }

    @Test
    @Tag("UnitTest")
    void shouldAcceptTemperaturesBetweenZeroAndOne() {
        Jurassic2Request requestWithZero = Jurassic2Request.builder().prompt("Hello")
                .temperature(0)
                .build();
        assertEquals(0.0, getTemperature(requestWithZero));


        Jurassic2Request requestWithOne = Jurassic2Request.builder().prompt("Hello")
                .temperature(1)
                .build();
        assertEquals(1.0, getTemperature(requestWithOne));
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
    @Tag("UnitTest")
    void invalidTemperature_ShouldThrow() {
        double tooLow = -0.1;
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> Jurassic2Request.builder().prompt("Test")
                        .temperature(tooLow)
                        .build()
        );

        double tooHigh = 1.1;
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> Jurassic2Request.builder().prompt("Test")
                        .temperature(tooHigh)
                        .build()
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
    }

    private static double getTemperature(Jurassic2Request request) {
        return new JSONObject(request.toSdkRequest().body().asUtf8String())
                .getDouble("temperature");
    }
}
