package aws.community.toolkits.bedrock;

import aws.community.toolkits.bedrock.common.FoundationModel;
import aws.community.toolkits.bedrock.providers.ailabs.Jurassic2Model;

public abstract class Models extends FoundationModel {
    public static final FoundationModel AILabsJurassic2 = new Jurassic2Model();

    protected Models(String modelId) {
        super(modelId);
    }
}
