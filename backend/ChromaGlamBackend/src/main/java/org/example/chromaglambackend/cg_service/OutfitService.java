package org.example.chromaglambackend.cg_service;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.Configuration;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.IterableStream;
import org.example.chromaglambackend.DAO.Outfit;
import org.example.chromaglambackend.DAO.OutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

@Service
public class OutfitService
{
    // token access
    private static final String TOKEN = "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";
    private static final String endpoint = "https://models.github.ai/inference";
    private static final String model = "openai/gpt-4o-mini";

    // repository
    private static OutfitRepository outfitRepository;

    @Autowired
    public OutfitService(OutfitRepository outfitRepository)
    {
        this.outfitRepository = outfitRepository;
    }

    // this method will contact the repository (DAO) layer to obtain
    // all the available items (null otherwise)
    public List<Outfit> getAllOutfits() {
        return outfitRepository.findAll();
    }

    public static void main(String[] args) {
        askFashionAdvice("casual");
    }

    public static void askFashionAdvice(String preferences)
    {
        OutfitService outfitService = new OutfitService(outfitRepository);

        // Get the latest weather data
        WeatherService weatherService = new WeatherService();
        WeatherData weatherData = weatherService.getWeatherDetails();
        String weatherForecast = weatherData.toString();

        // Get all available items
        ArrayList<Outfit> outfits = (ArrayList<Outfit>) outfitService.getAllOutfits();

        // Convert available outfit items to a comma-separated list
        StringBuilder itemsBuilder = new StringBuilder();
        for (Outfit item : outfits) {
            if(item.getAvailable() == 1)
                itemsBuilder.append(item.getDescription()).append(", ");
        }

        // Remove trailing comma and space
        String outfitItems = !itemsBuilder.isEmpty()
                ? itemsBuilder.substring(0, itemsBuilder.length() - 2)
                : "No items available";

        // Build the client
        ChatCompletionsClient client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(TOKEN))
                .endpoint(endpoint)
                .buildClient();

        // Chat history
        List<ChatRequestMessage> chatMessages = new ArrayList<>();

        // Set assistant behavior
        chatMessages.add(new ChatRequestSystemMessage("You are a helpful fashion assistant. "
                + "Match available outfit items with the weather and user preferences. "
                + "Respond concisely with your outfit suggestions."));

        // Provide the user's wardrobe
        chatMessages.add(new ChatRequestUserMessage("These are the items in my wardrobe: " + outfitItems));

        // Provide weather forecast
        chatMessages.add(new ChatRequestUserMessage("This is the weather forecast: " + weatherForecast));

        // Provide user preferences
        chatMessages.add(new ChatRequestUserMessage("These are my clothing preferences: " + preferences));

        // Ask the actual fashion advice question
        chatMessages.add(new ChatRequestUserMessage("What should I wear today?"));

        // Prepare request
        ChatCompletionsOptions options = new ChatCompletionsOptions(chatMessages);
        options.setModel(model);

        // Call the model
        ChatCompletions response = client.complete(options);

        // Output response
        String reply = response.getChoice().getMessage().getContent();
        System.out.println("Assistant: " + reply);
    }



}
