package DTO;

import cg_service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserController
{
    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

 /*   public ResponseEntity<?>  getUserById(long id)
    {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("User not found"));
    }

   @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam name,@RequestParam String username,
                                           @RequestParam String password) {
        userService.registerUser(name,username,email, password);
        return ResponseEntity.ok("User registered successfully.");
    }

  */
}
