package scenarios.ailabs.jurassic2;

import aws.community.toolkits.bedrock.SimpleBedrockClient;
import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Request;
import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

import static aws.community.toolkits.bedrock.Models.AILABS_JURASSIC_2;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class IT_Scenario_Jurassic_2 {

    private static SimpleBedrockClient client;

    @BeforeAll
    static void setup() {
        client = new SimpleBedrockClient();
    }

    @Test
    void simpleInvocation() {
        String prompt = "Hello";

        String simpleCompletion = client.invokeModel(AILABS_JURASSIC_2, prompt);

        assertNotNull(simpleCompletion);
    }

    @Test
    void builderWithHighLevel() {
        Jurassic2Request request = Jurassic2Request.builder()
                .prompt("Hello")
                .temperature(0.5)
                .build();

        Jurassic2Response response = client.invokeModel(request);

        String highLevelCompletion = response.completion();

        assertNotNull(highLevelCompletion);
    }

    @Test
    void builderWithLowLevel() {
        Jurassic2Request request = Jurassic2Request.builder()
                .prompt("Hello")
                .temperature(0.5)
                .build();

        Jurassic2Response response = client.invokeModel(request);

        InvokeModelResponse sdkResponse = response.sdkResponse();

        JSONObject responseBody = new JSONObject(sdkResponse.body().asUtf8String());

        String lowLevelCompletion = responseBody
                .getJSONArray("completions")
                .getJSONObject(0)
                .getJSONObject("data")
                .getString("text");

        assertNotNull(lowLevelCompletion);
    }
}
