package aws.community.toolkits.bedrock;

import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Model;

public class Models extends FoundationModel {
    public static final FoundationModel AILABS_JURASSIC_2 = new Jurassic2Model();

    protected Models(String modelId) {
        super(modelId);
    }
}
