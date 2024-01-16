package aws.community.toolkits.bedrock.common;

public record TextGenerationConfig(double temperature) {
    public static final TextGenerationConfig DEFAULT = new TextGenerationConfig(0);
}
