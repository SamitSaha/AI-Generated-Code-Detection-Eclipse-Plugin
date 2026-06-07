package com.pdeplugin.mitfinalproject.service;

import java.util.ArrayList;
import java.util.List;

import com.pdeplugin.mitfinalproject.config.AppConfig;
import com.pdeplugin.mitfinalproject.llm.HuggingFaceClient;
import com.pdeplugin.mitfinalproject.model.ModelResponse;
import com.pdeplugin.mitfinalproject.model.EnsembleResult;

public class AIAnalysisService {

    private final HuggingFaceClient client = new HuggingFaceClient();
    private final FeatureExtractionService promptService = new FeatureExtractionService();
    private final EnsembleScoringService scoringService = new EnsembleScoringService();

    public List<ModelResponse> analyze(String code) {

        List<ModelResponse> results = new ArrayList<>();

        String prompt = promptService.buildPrompt(code);

        for (String model : AppConfig.MODELS) {

            try {

                String response = client.callModel(model, prompt);

                int percentage =
                        scoringService.extractPercentage(response);

                double aiProb =
                        scoringService.extractDouble(response, "ai_probability");

                double humanProb =
                        scoringService.extractDouble(response, "human_probability");

                double confidence =
                        scoringService.extractDouble(response, "confidence");

                results.add(
                        new ModelResponse(
                                model,
                                response,
                                percentage,
                                aiProb,
                                humanProb,
                                confidence
                        )
                );

            } catch (Exception e) {

                results.add(
                        new ModelResponse(
                                model,
                                "FAILED: " + e.getMessage(),
                                0,
                                0,
                                0,
                                0
                        )
                );
            }
        }

        return results;
    }


    public EnsembleResult computeFinalResult(List<ModelResponse> results) {

        return scoringService.computeFinal(results);
    }
}

