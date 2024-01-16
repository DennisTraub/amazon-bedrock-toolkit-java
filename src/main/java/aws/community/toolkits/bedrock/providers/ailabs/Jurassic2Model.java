package aws.community.toolkits.bedrock.providers.ailabs;

import aws.community.toolkits.bedrock.FoundationModel;
import aws.community.toolkits.bedrock.common.TextGenerationConfig;

public class Jurassic2Model extends FoundationModel {
    public static final String MODEL_ID = "ai21.j2-mid-v1";
    public static final double DEFAULT_TEMPERATURE = 0.5;
    public static final double MIN_TEMPERATURE = 0;
    public static final double MAX_TEMPERATURE = 1;

    public Jurassic2Model() {
        super(MODEL_ID);
    }

    public TextGenerationConfig defaultConfig() {
        return new TextGenerationConfig(DEFAULT_TEMPERATURE);
    }

    public void validate(TextGenerationConfig config) {
        if(config.temperature() < MIN_TEMPERATURE) {
            throw new IllegalArgumentException("Temperature must be >= 0");
        } else if (config.temperature() > MAX_TEMPERATURE) {
            throw new IllegalArgumentException("Temperature must be <= 1");
        }
    }
}
