package org.example.chromaglambackend.DTO;

import org.example.chromaglambackend.DAO.Outfit;
import org.example.chromaglambackend.cg_service.OutfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/outfits")
public class OutfitController
{

    private final OutfitService outfitService;

    @Autowired
    public OutfitController(OutfitService outfitService) {
        this.outfitService = outfitService;
    }

    @GetMapping(value = "/get/all_outfits")
    public List<Outfit> getAllOutfits() {
        ArrayList<Outfit> outfits = (ArrayList<Outfit>) outfitService.getAllOutfits();
        for(Outfit outfit : outfits) {
            System.out.println(outfit.toString());
        }
        return outfits;
    }

    @GetMapping(value = "/get/best_outfit")
    public Outfit getBestOutfit() {
        return outfitService.getBestOutfit();
    }

    @GetMapping(value = "/get/other_outfit")
    public Outfit getOtherOutfit() {
        return outfitService.getOtherOutfit();
    }

    @GetMapping(value = "/get/old_outfit")
    public Outfit getOldOutfit(){
        return outfitService.getOldOutfit();
    }
}
