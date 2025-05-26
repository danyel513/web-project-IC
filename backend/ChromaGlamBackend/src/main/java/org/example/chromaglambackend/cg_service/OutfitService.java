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

   
}
