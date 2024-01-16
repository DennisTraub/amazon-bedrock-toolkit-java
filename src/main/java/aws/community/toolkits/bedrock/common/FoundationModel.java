package aws.community.toolkits.bedrock.common;

public abstract class FoundationModel {

    private final String modelId;

    protected FoundationModel(String modelId) {
        this.modelId = modelId;
    }

    public String modelId() {
        return modelId;
    }

    public abstract void validate(TextGenerationConfig config);

    public abstract TextGenerationConfig defaultConfig();
}
