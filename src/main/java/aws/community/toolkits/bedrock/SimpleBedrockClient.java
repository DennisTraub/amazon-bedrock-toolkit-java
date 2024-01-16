package aws.community.toolkits.bedrock;

import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Request;
import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Response;
import org.json.JSONObject;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

public class SimpleBedrockClient {

    private static final Region DEFAULT_REGION = Region.US_EAST_1;
    private final BedrockRuntimeClient bedrockRuntimeClient;

    public SimpleBedrockClient() {
        this.bedrockRuntimeClient = software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(DEFAULT_REGION)
                .build();
    }

    public String invokeModel(FoundationModel model, String prompt) {
        InvokeModelRequest request;

        if (model.equals(Models.AILABS_JURASSIC_2)) {
            String payload = new JSONObject()
                    .put("prompt", prompt)
                    .put("temperature", 0.5)
                    .put("maxTokens", 200)
                    .toString();

            request = InvokeModelRequest.builder()
                    .modelId(model.modelId())
                    .accept("application/json")
                    .contentType("application/json")
                    .body(SdkBytes.fromUtf8String(payload))
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid model");
        }

        InvokeModelResponse response = bedrockRuntimeClient.invokeModel(request);

        JSONObject responseBody = new JSONObject(response.body().asUtf8String());

        return responseBody
                .getJSONArray("completions")
                .getJSONObject(0)
                .getJSONObject("data")
                .getString("text");
    }

    public Jurassic2Response invokeModel(Jurassic2Request request) {
        InvokeModelRequest sdkRequest = request.toSdkRequest();
        InvokeModelResponse response = bedrockRuntimeClient.invokeModel(sdkRequest);
        return new Jurassic2Response(response);
    }
}
