package aws.community.toolkits.bedrock.tools.validation;

public class ModelConfigValidator {
    public static void validateTemperature(double temperature, double min, double max) {
        if(temperature < min) {
            throw new IllegalArgumentException("Temperature must be >= " + min);
        } else if (temperature > max) {
            throw new IllegalArgumentException("Temperature must be <= " + max);
        }
    }
}
