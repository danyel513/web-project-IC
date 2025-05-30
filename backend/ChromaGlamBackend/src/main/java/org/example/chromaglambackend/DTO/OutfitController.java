package org.example.chromaglambackend.DTO;

import org.example.chromaglambackend.DAO.Outfit;
import org.example.chromaglambackend.DAO.User;
import org.example.chromaglambackend.cg_service.OutfitService;
import org.example.chromaglambackend.cg_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/outfits")
public class OutfitController
{
    private final OutfitService outfitService;
    private final UserService userService;

    @Autowired
    public OutfitController(OutfitService outfitService, UserService userService) {
        this.outfitService = outfitService;
        this.userService = userService;
    }

    @GetMapping(value = "/get/all_outfits")
    public List<Outfit> getAllOutfits() {
        ArrayList<Outfit> outfits = (ArrayList<Outfit>) outfitService.getAllOutfits();
        for(Outfit outfit : outfits) {
            System.out.println(outfit.toString());
        }
        return outfits;
    }

    @GetMapping(value = "/get/outfit")
    public String getOutfitByUsername(@RequestParam String username)
    {
        User user = userService.getUser(username);
        String outfit = outfitService.askFashionAdvice(user.getPreferences());
        return outfit;
    }

    @GetMapping(value = "/get/image")
    public ResponseEntity<byte[]> getImageById(@RequestParam long id)
    {
        byte[] image = outfitService.getImageById(id);
        if (image == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG) // or PNG, depending on file
                .body(image);
    }

    @PutMapping(value = "/updateAvailability")
    public ResponseEntity<Boolean> updateAvailabilityById(@RequestParam long id) {
        try {
            boolean updated = outfitService.toggleAvailabilityById(id);
            if (updated) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
}
