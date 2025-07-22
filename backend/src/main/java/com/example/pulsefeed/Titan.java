package com.example.pulsefeed;

import org.json.JSONObject;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

public class Titan {
     public static final String MODEL_ID = "amazon.titan-text-express-v1";

    public static String invoke(BedrockRuntimeClient client, String prompt, double temperature, int maxTokens) {
        JSONObject jsonBody = new JSONObject()
            .put("inputText", prompt)
            .put("textGenerationConfig", new JSONObject()
                    .put("temperature", temperature)
                    .put("maxTokenCount", maxTokens)
                    .put("topP", 0.9));

        InvokeModelRequest request = InvokeModelRequest.builder()
                .modelId(MODEL_ID)
                .contentType("application/json")
                .accept("application/json")
                .body(SdkBytes.fromUtf8String(jsonBody.toString()))
                .build();

        InvokeModelResponse response = client.invokeModel(request);


                JSONObject responseJson = new JSONObject(response.body().asUtf8String());
String summary = responseJson
    .getJSONArray("results")
    .getJSONObject(0)
    .getString("outputText");


        return summary;
    }
}
