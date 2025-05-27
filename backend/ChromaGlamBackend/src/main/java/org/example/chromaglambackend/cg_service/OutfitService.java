package org.example.chromaglambackend.cg_service;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.*;
import com.azure.core.credential.AzureKeyCredential;
import org.example.chromaglambackend.DAO.Outfit;
import org.example.chromaglambackend.DAO.OutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class OutfitService
{
    // token access
    private final String TOKEN = "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";
    private final String endpoint = "https://models.github.ai/inference";
    private final String model = "openai/gpt-4o-mini";

    // repository
    private final OutfitRepository outfitRepository;

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

    public byte[] getImageById(long id)
    {
        byte[] image = null;
        Outfit outfit = outfitRepository.findById(id);
        // assign the image saved on backend by the path got from database
        if (outfit != null && outfit.getImage() != null) {
            try {
                Path imagePath = Paths.get(outfit.getImage());
                image = Files.readAllBytes(imagePath);
            } catch (IOException e) {
                e.printStackTrace(); // log error appropriately
            }
        }
        return image;
    }
  
    public Outfit getBestOutfit()
    {
        return new Outfit();
    }

    public Outfit getOldOutfit()
    {
        return new Outfit();
    }

    public Outfit getOtherOutfit()
    {
        return new Outfit();
    }

   public String askFashionAdvice(String preferences)
    {
        // Get the latest weather data
        WeatherService weatherService = new WeatherService();
        WeatherData weatherData = weatherService.getWeatherDetails();
        String weatherForecast = weatherData.toString();

        // Get all available items
        ArrayList<Outfit> outfits = (ArrayList<Outfit>) getAllOutfits();

        // Convert available outfit items to a comma-separated list
        StringBuilder itemsBuilder = new StringBuilder();
        for (Outfit item : outfits) {
            if(item.getAvailable() == 1)
                // format -> id:description
                itemsBuilder.append(item.getItem_id()).append(":").append(item.getDescription()).append(", ");
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
                + "Respond concisely with your outfit suggestions. "
                + "The response should contain only the id of items (the part before :). "
                + "Respect this format: id1/id2/id3/.../idn. "
                + "You are not allowed to choose two items from the same category (for example two t-shirts, or two hoodies). "
                + "Also you must choose one of the necessary items such as t-shirt or hoodie, a pair of pants, shoes, the rest are optional."
        ));

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
        return reply;
    }
}
