package com.pdeplugin.mitfinalproject.service;

public class FeatureExtractionService {
	public String buildPrompt(String code) {

        return """
You are an expert forensic linguist and AI text detection analyst.

Your task is to determine whether the given text is more likely:
(A) Human-written
(B) AI-generated

You must analyze the text using the following criteria:

1. Lexical diversity (vocabulary variation)
2. Sentence structure variation
3. Perplexity patterns (predictability)
4. Burstiness (variation in sentence length)
5. Repetition patterns
6. Overly formal or overly balanced tone
7. Logical flow smoothness
8. Generic phrasing vs specific lived experience
9. Emotional authenticity
10. Statistical uniformity

IMPORTANT RULES:
- Do NOT guess.
- Provide AI-likeness percentage 0-100.
- Base your conclusion only on linguistic evidence.
- If uncertain, reflect that in probability score.
- Avoid political or ethical commentary.
- If AI probability is between 0.45 and 0.55, classify as "Uncertain".
- Return STRICT JSON only.

Output format:

{
  "prediction": "AI" or "Human" or "Uncertain",
  "AI_MATCH_PERCENTAGE": <number>,
  "confidence": 0-100,
  "ai_probability": 0-1,
  "human_probability": 0-1,
  "analysis": {
      "lexical_diversity": "...",
      "burstiness": "...",
      "repetition": "...",
      "tone_naturalness": "...",
      "structure_variation": "...",
      "overall_reasoning": "..."
  }
}

Now analyze the following code:
""" + code;
    }
}

