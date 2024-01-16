package aws.community.toolkits.bedrock.providers.ailabs;

import aws.community.toolkits.bedrock.Models;
import aws.community.toolkits.bedrock.tools.validation.ModelConfigValidator;
import aws.community.toolkits.bedrock.tools.validation.PromptValidator;
import org.json.JSONObject;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;

public class Jurassic2Request {
    private final String modelId = Models.AILABS_JURASSIC_2.modelId();
    private final String contentType;
    private final String accept;
    private final SdkBytes body;

    private Jurassic2Request(BuilderImpl builder) {
        this.body = builder.body;
        this.contentType = builder.contentType;
        this.accept = builder.accept;
    }

    public static Builder builder() {
        return new BuilderImpl();
    }

    public InvokeModelRequest toSdkRequest() {
        return InvokeModelRequest.builder()
                .body(this.body)
                .modelId(this.modelId)
                .contentType(this.contentType)
                .accept(this.accept)
                .build();
    }

    static final class BuilderImpl extends Builder {
        private SdkBytes body;
        private final String accept;
        private final String contentType;

        private BuilderImpl() {
            accept = "application/json";
            contentType = "application/json";
        }

        public Jurassic2Request build() {
            if (this.prompt == null) {
                throw new IllegalStateException("Prompt is required");
            }

            String payload = new JSONObject()
                    .put("prompt", this.prompt)
                    .put("temperature", this.temperature)
                    .put("maxTokens", 200)
                    .toString();

            this.body = SdkBytes.fromUtf8String(payload);

            return new Jurassic2Request(this);
        }
    }

    public abstract static class Builder {
        String prompt;
        double temperature = 0.5;

        public Builder prompt(String prompt) {
            PromptValidator.validate(prompt);
            this.prompt = prompt;
            return this;
        }

        public abstract Jurassic2Request build();

        public Builder temperature(double temperature) {
            ModelConfigValidator.validateTemperature(
                    temperature,
                    Jurassic2Model.MIN_TEMPERATURE,
                    Jurassic2Model.MAX_TEMPERATURE
            );
            this.temperature = temperature;
            return this;
        }
    }
}
