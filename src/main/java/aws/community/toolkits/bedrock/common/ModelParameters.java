package aws.community.toolkits.bedrock.common;

public final class ModelParameters {
    public record Temperature(double defaultValue, double minimumValue, double maximumValue) {}
    public record TopP(double defaultValue, double minimumValue, double maximumValue) {}
}
