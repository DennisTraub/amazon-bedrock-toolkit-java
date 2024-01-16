package aws.community.toolkits.bedrock.providers.ailabs;

import org.json.JSONObject;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

public class Jurassic2Response {
    private final InvokeModelResponse sdkResponse;
    private final String completion;

    public Jurassic2Response(InvokeModelResponse response) {
        this.sdkResponse = response;

        JSONObject responseBody = new JSONObject(response.body().asUtf8String());

        this.completion = responseBody
                .getJSONArray("completions")
                .getJSONObject(0)
                .getJSONObject("data")
                .getString("text");
    }

    public InvokeModelResponse sdkResponse() {
        return sdkResponse;
    }

    public String completion() {
        return completion;
    }
}
