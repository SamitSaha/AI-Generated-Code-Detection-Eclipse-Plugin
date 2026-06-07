package com.pdeplugin.mitfinalproject.model;

public class EnsembleResult {

    private final String prediction;
    private final double finalAiScore;
    private final double agreement;

    public EnsembleResult(String prediction,
                          double finalAiScore,
                          double agreement) {

        this.prediction = prediction;
        this.finalAiScore = finalAiScore;
        this.agreement = agreement;
    }

    public String getPrediction() {
        return prediction;
    }

    public double getFinalAiScore() {
        return finalAiScore;
    }

    public double getAgreement() {
        return agreement;
    }

    public int getFinalPercentage() {
        return (int)(finalAiScore * 100);
    }
}
