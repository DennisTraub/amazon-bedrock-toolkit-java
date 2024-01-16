package aws.community.toolkits.bedrock.providers.ailabs;

import aws.community.toolkits.bedrock.Models;
import org.json.JSONObject;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;

public class Jurassic2Request {
    private final String modelId = Models.AILABS_JURASSIC_2.modelId();
    private final SdkBytes body;
    private final String contentType;
    private final String accept;

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
        private String contentType;
        private String accept;

        private BuilderImpl() {}

        public Jurassic2Request build() {
            if (this.prompt == null) {
                throw new IllegalStateException("Prompt is required");
            }

            String payload = new JSONObject()
                    .put("prompt", this.prompt)
                    .put("temperature", 0.5)
                    .put("maxTokens", 200)
                    .toString();

            this.body = SdkBytes.fromUtf8String(payload);

            return new Jurassic2Request(this);
        }
    }

    public abstract static class Builder {
        String prompt;

        public Builder prompt(String prompt) {
            if(prompt == null) {
                throw new IllegalArgumentException("Prompt cannot be null");
            }
            if(prompt.trim().isEmpty()) {
                throw new IllegalArgumentException("Prompt cannot be empty");
            }
            this.prompt = prompt;
            return this;
        }

        public abstract Jurassic2Request build();
    }
}
