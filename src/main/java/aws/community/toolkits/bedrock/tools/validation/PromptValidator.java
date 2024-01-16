package aws.community.toolkits.bedrock.tools.validation;

public class PromptValidator {
    public static void validate(String prompt) {
        if(prompt == null) {
            throw new IllegalArgumentException("Prompt cannot be null");
        }
        if(prompt.isBlank()) {
            throw new IllegalArgumentException("Prompt cannot be empty");
        }
    }
}
