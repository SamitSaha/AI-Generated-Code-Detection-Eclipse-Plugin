package com.pdeplugin.mitfinalproject.service;

import java.util.List;

import com.pdeplugin.mitfinalproject.model.ModelResponse;
import com.pdeplugin.mitfinalproject.model.EnsembleResult;

public class EnsembleScoringService {

    public int extractPercentage(String text) {

        try {
            for (String line : text.split("\n")) {

                if (line.contains("AI_MATCH_PERCENTAGE")) {

                    return Integer.parseInt(
                            line.replaceAll("\\D+", "")
                    );
                }
            }
        } catch (Exception ignored) {}

        return 0;
    }


    public double extractDouble(String json, String field) {

        try {

            String marker = "\"" + field + "\"";
            int start = json.indexOf(marker);

            if (start == -1) return 0;

            start = json.indexOf(":", start) + 1;

            while (start < json.length() &&
                    Character.isWhitespace(json.charAt(start))) {
                start++;
            }

            int end = start;

            while (end < json.length() &&
                    (Character.isDigit(json.charAt(end)) ||
                     json.charAt(end) == '.')) {
                end++;
            }

            return Double.parseDouble(
                    json.substring(start, end)
            );

        } catch (Exception e) {

            return 0;
        }
    }


    public EnsembleResult computeFinal(List<ModelResponse> results) {

        double totalAiProb = 0;

        int aiVotes = 0;
        int humanVotes = 0;

        for (ModelResponse r : results) {

            totalAiProb += r.getAiProbability();

            if (r.getAiProbability() >= 0.5)
                aiVotes++;
            else
                humanVotes++;
        }

        double avgAiProb = totalAiProb / results.size();

        String prediction =
                avgAiProb >= 0.5
                ? "AI Generated"
                : "Human Written";

        int majority = Math.max(aiVotes, humanVotes);

        double agreement =
                (double) majority / results.size();

        return new EnsembleResult(
                prediction,
                avgAiProb,
                agreement
        );
    }
}

