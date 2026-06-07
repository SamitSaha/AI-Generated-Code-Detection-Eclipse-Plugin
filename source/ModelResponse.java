package com.pdeplugin.mitfinalproject.model;

//public class ModelResponse {
//	private final String model;
//    private final String content;
//    private final int percentage;
//
//    public ModelResponse(String model, String content, int percentage) {
//        this.model = model;
//        this.content = content;
//        this.percentage = percentage;
//    }
//
//    public String getModel() { return model; }
//    public String getContent() { return content; }
//    public int getPercentage() { return percentage; }
//}
public class ModelResponse {

    private final String model;
    private final String rawContent;
//    private final int percentage;

    private final double aiProbability;
    private final double humanProbability;
    private final double confidence;

    public ModelResponse(
        String model,
        String rawContent,
        int percentage,
        double aiProbability,
        double humanProbability,
        double confidence
    ) {
        this.model = model;
        this.rawContent = rawContent;
//        this.percentage = percentage;
        this.aiProbability = aiProbability;
        this.humanProbability = humanProbability;
        this.confidence = confidence;
    }

    public String getModel() { return model; }
    public String getRawContent() { return rawContent; }
    public double getAiProbability() { return aiProbability; }
    public double getHumanProbability() { return humanProbability; }
    public double getConfidence() { return confidence; }

    public int getPercentage() {
        return (int)(aiProbability * 100);
    }
}