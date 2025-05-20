package org.example.chromaglambackend.DTO;

import org.example.chromaglambackend.DAO.Outfit;
import org.example.chromaglambackend.cg_service.OutfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/get")
    public List<Outfit> getAllOutfits() {
        return outfitService.getAllOutfits();
    }

    @GetMapping(value = "/get")
    public Outfit getBestOutfit() {
        return outfitService.getBestOutfit();
    }

    @GetMapping(value = "/get")
    public Outfit getOtherOutfit() {
        return outfitService.getOtherOutfit();
    }

    @GetMapping(value = "/get")
    public Outfit getOldOutfit(){
        return outfitService.getOldOutfit();
    }
}
