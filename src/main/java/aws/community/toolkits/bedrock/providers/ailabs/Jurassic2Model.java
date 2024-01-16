package aws.community.toolkits.bedrock.providers.ailabs;

import aws.community.toolkits.bedrock.common.FoundationModel;
import aws.community.toolkits.bedrock.common.ModelParameters;
import aws.community.toolkits.bedrock.common.ModelParameters.Temperature;

public class Jurassic2Model extends FoundationModel {
    public static final String MODEL_ID = "ai21.j2-mid-v1";
    static final double DEFAULT_TEMPERATURE = 0.5;
    static final double MIN_TEMPERATURE = 0;
    static final double MAX_TEMPERATURE = 1;
    static final double DEFAULT_TOP_P = 0.5;
    static final double MIN_TOP_P = 0;
    static final double MAX_TOP_P = 1;

    public Jurassic2Model() {
        super(MODEL_ID);
    }

    public Temperature temperature() {
        return new Temperature(DEFAULT_TEMPERATURE, MIN_TEMPERATURE, MAX_TEMPERATURE);
    }

    public ModelParameters.TopP topP() {
        return new ModelParameters.TopP(DEFAULT_TOP_P, MIN_TOP_P, MAX_TOP_P);
    }
}
