package aws.community.toolkits.bedrock;

public abstract class FoundationModel {
    private final String modelId;

    protected FoundationModel(String modelId) {
        this.modelId = modelId;
    }

    public String modelId() {
        return modelId;
    }
}
