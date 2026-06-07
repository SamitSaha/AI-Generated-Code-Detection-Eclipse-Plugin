package com.pdeplugin.mitfinalproject.service;

import java.util.ArrayList;
import java.util.List;

import com.pdeplugin.mitfinalproject.config.AppConfig;
import com.pdeplugin.mitfinalproject.llm.HuggingFaceClient;
import com.pdeplugin.mitfinalproject.model.HighlightResult;

public class CodeHighlightService {

    private final HuggingFaceClient client = new HuggingFaceClient();

    public HighlightResult detectAiLines(String code) {

        try {

            String prompt = buildPrompt(code);

            // call first model only
            String model = AppConfig.MODELS[0];

            String response = client.callModel(model, prompt);

            List<Integer> lines = parseLines(response);

            return new HighlightResult(lines);

        } catch (Exception e) {

            return new HighlightResult(new ArrayList<>());
        }
    }

    private String buildPrompt(String code) {

        return """
You are an AI code forensic expert.

Analyze the following code and determine which lines appear AI generated.

Return ONLY this format:

AI_LINES: 2,5,8

Do not explain anything.
Do not include extra text.

Code:
""" + code;
    }

    private List<Integer> parseLines(String text) {

        List<Integer> lines = new ArrayList<>();

        try {

            if (!text.contains("AI_LINES")) return lines;

            String numbers = text.substring(text.indexOf(":") + 1).trim();

            String[] parts = numbers.split(",");

            for (String p : parts) {

                lines.add(Integer.parseInt(p.trim()));
            }

        } catch (Exception ignored) {}

        return lines;
    }
}