package com.pdeplugin.mitfinalproject.model;

import java.util.List;

public class HighlightResult {

    private final List<Integer> aiLines;

    public HighlightResult(List<Integer> aiLines) {
        this.aiLines = aiLines;
    }

    public List<Integer> getAiLines() {
        return aiLines;
    }
}