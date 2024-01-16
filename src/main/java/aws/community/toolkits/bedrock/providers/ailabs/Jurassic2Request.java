package aws.community.toolkits.bedrock.providers.ailabs;

import aws.community.toolkits.bedrock.Models;
import aws.community.toolkits.bedrock.tools.validation.ModelConfigValidator;
import aws.community.toolkits.bedrock.tools.validation.PromptValidator;
import org.json.JSONObject;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;

public class Jurassic2Request {
    private final String modelId = Models.AILabsJurassic2.modelId();
    private final String contentType;
    private final String accept;
    private final SdkBytes body;

    private Jurassic2Request(Builder builder) {
        this.body = builder.body;
        this.contentType = builder.contentType;
        this.accept = builder.accept;
    }

    public static Builder builder() {
        return new Builder();
    }

    public InvokeModelRequest toSdkRequest() {
        return InvokeModelRequest.builder()
                .body(this.body)
                .modelId(this.modelId)
                .contentType(this.contentType)
                .accept(this.accept)
                .build();
    }

    public static final class Builder {
        private SdkBytes body;
        private final String accept;
        private final String contentType;
        String prompt;
        double temperature = 0.5;

        private Builder() {
            accept = "application/json";
            contentType = "application/json";
        }

        public Builder prompt(String prompt) {
            PromptValidator.validate(prompt);
            this.prompt = prompt;
            return this;
        }

        public Builder temperature(double temperature) {
            ModelConfigValidator.validateTemperature(
                    temperature,
                    Jurassic2Model.MIN_TEMPERATURE,
                    Jurassic2Model.MAX_TEMPERATURE
            );
            this.temperature = temperature;
            return this;
        }

        public Jurassic2Request build() {
            if (this.prompt == null) {
                throw new IllegalStateException("Prompt is required");
            }

            String payload = new JSONObject()
                    .put("prompt", this.prompt)
                    .put("temperature", this.temperature)
                    .toString();

            this.body = SdkBytes.fromUtf8String(payload);

            return new Jurassic2Request(this);
        }
    }
}
