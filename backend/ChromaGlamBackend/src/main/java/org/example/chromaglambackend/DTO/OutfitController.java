package org.example.chromaglambackend.DTO;

import org.example.chromaglambackend.DAO.Outfit;
import org.example.chromaglambackend.cg_service.OutfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/get")
    public List<Outfit> getAllOutfits() {
        return outfitService.getAllOutfits();
    }



    //outfit generating will call an internal service which will return the response of the model to the request
//    @PostMapping(value = "/generate", consumes = "application/json")
//    public ResponseEntity<HttpStatus> outfit_generating(@RequestParam int outfit_id)
//    {
//
//    }
}
