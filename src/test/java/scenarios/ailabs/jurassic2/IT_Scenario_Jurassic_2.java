package scenarios.ailabs.jurassic2;

import aws.community.toolkits.bedrock.SimpleBedrockClient;
import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Request;
import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

import static aws.community.toolkits.bedrock.Models.AILABS_JURASSIC_2;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class IT_Scenario_Jurassic_2 {

    @Test
    void Scenarios() {
        SimpleBedrockClient client = new SimpleBedrockClient();
        String prompt = "Hello";

        // Simple invocation returns completion
        String simpleCompletion = client.invokeModel(AILABS_JURASSIC_2, prompt);

        // Using builder returns response object
        Jurassic2Request request = Jurassic2Request.builder().prompt("Hello").build();
        Jurassic2Response response = client.invokeModel(request);

        // Response object provides high-level API to get completion
        String highLevelCompletion = response.completion();

        // Response object provides low-level API from AWS SDK
        InvokeModelResponse sdkResponse = response.sdkResponse();
        JSONObject responseBody = new JSONObject(sdkResponse.body().asUtf8String());
        String lowLevelCompletion = responseBody
                .getJSONArray("completions")
                .getJSONObject(0)
                .getJSONObject("data")
                .getString("text");

        assertNotNull(lowLevelCompletion);
    }




    @Test
    void InvokeWithRequestBuilderShouldReturnResponseWithLowLevelSdkResponse() {
        SimpleBedrockClient client = new SimpleBedrockClient();

        Jurassic2Request request = Jurassic2Request.builder()
                .prompt("Hello")
                .build();

        Jurassic2Response response = client.invokeModel(request);

        InvokeModelResponse sdkResponse = response.sdkResponse();

        assertNotNull(sdkResponse);
        System.out.println(sdkResponse);
    }

    @Test
    void InvokeWithoutPromptShouldThrow() {
        assertThrowsExactly(IllegalStateException.class, () -> Jurassic2Request.builder().build());
    }

    @Test
    void InvokeWithNullPromptShouldThrow() {
        assertThrowsExactly(IllegalArgumentException.class, () -> Jurassic2Request.builder().prompt(null).build());
    }

    @Test
    void InvokeWithEmptyPromptShouldThrow() {
        assertThrowsExactly(IllegalArgumentException.class, () -> Jurassic2Request.builder().prompt("").build());
    }



}
