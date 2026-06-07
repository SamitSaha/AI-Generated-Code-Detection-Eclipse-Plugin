package com.pdeplugin.mitfinalproject.controller;

import java.util.List;

import com.pdeplugin.mitfinalproject.model.ModelResponse;
import com.pdeplugin.mitfinalproject.service.AIAnalysisService;
import com.pdeplugin.mitfinalproject.model.EnsembleResult;

import com.pdeplugin.mitfinalproject.service.CodeHighlightService;
import com.pdeplugin.mitfinalproject.model.HighlightResult;

public class AnalysisController {

    private final AIAnalysisService service = new AIAnalysisService();
    private final CodeHighlightService highlightService = new CodeHighlightService();

    public List<ModelResponse> analyzeCode(String code) {
        return service.analyze(code);
    }
    
    public EnsembleResult computeFinal(List<ModelResponse> results) {
        return service.computeFinalResult(results);
    }
    
    public HighlightResult detectAiLines(String code) {
        return highlightService.detectAiLines(code);
    }
}
