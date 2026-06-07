package com.pdeplugin.mitfinalproject.llm;

import java.net.URI;
import java.net.http.*;

import com.pdeplugin.mitfinalproject.config.AppConfig;
import com.pdeplugin.mitfinalproject.util.JsonUtils;

public class HuggingFaceClient {
	public String callModel(String model, String prompt) throws Exception {

        String body = "{"
                + "\"model\":\"" + model + "\","
                + "\"messages\":[{\"role\":\"user\",\"content\":\""
                + JsonUtils.escape(prompt) + "\"}]"
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AppConfig.HF_API_URL))
                .header("Authorization", "Bearer " + AppConfig.HF_TOKEN)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return extractAssistantContent(response.body());
    }
	
	private String extractAssistantContent(String json) {

        String marker = "\"content\":\"";
        int start = json.indexOf(marker);
        if (start == -1) return json;

        start += marker.length();
        StringBuilder out = new StringBuilder();
        boolean escaped = false;

        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);

            if (escaped) {
                if (c == 'n') out.append('\n');
                else if (c == 't') out.append('\t');
                else out.append(c);
                escaped = false;
                continue;
            }

            if (c == '\\') { escaped = true; continue; }
            if (c == '"') break;

            out.append(c);
        }

        return out.toString();
    }
}
