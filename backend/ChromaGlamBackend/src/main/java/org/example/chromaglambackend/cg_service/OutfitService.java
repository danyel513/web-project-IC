package org.example.chromaglambackend.cg_service;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestSystemMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.StreamingChatCompletionsUpdate;
import com.azure.ai.inference.models.StreamingChatResponseMessageUpdate;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.IterableStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OutfitService
{

    private static final String GITHUB_TOKEN = "1";
    public static void main(String[] args) {

        // Fetch your GitHub PAT (token) from environment variables
        String key = GITHUB_TOKEN;
        String endpoint = "https://models.github.ai/inference";
        String model = "openai/gpt-4o-mini";

        // Create the client using endpoint and credentials
        ChatCompletionsClient client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();

        // Store chat history
        List<ChatRequestMessage> chatHistory = new ArrayList<>();
        // Add a system message to set behavior
        chatHistory.add(new ChatRequestSystemMessage("You are a helpful assistant."));

        Scanner scanner = new Scanner(System.in);

        // Infinite chat loop
        while (true) {
            System.out.print("\nYou: ");
            String userInput = scanner.nextLine();

            if (userInput == null || userInput.isEmpty()) {
                break; // Exit on empty input
            }

            // Add user input to chat history
            chatHistory.add(new ChatRequestUserMessage(userInput));

            // Create options for the API call
            ChatCompletionsOptions options = new ChatCompletionsOptions(chatHistory);
            options.setModel(model);

            // Stream response from model
            IterableStream<StreamingChatCompletionsUpdate> stream = client.completeStream(options);

            StringBuilder assistantReply = new StringBuilder();

            stream.stream().forEach(update -> {
                if (!CoreUtils.isNullOrEmpty(update.getChoices())) {
                    StreamingChatResponseMessageUpdate delta = update.getChoice().getDelta();

                    if (delta.getContent() != null) {
                        System.out.print(delta.getContent()); // Print token by token
                        assistantReply.append(delta.getContent()); // Build full response
                    }
                }
            });

            // Add assistant response back to history
            chatHistory.add(new com.azure.ai.inference.models.ChatRequestAssistantMessage(assistantReply.toString()));
        }

        scanner.close();
        }
}
