package org.example.chromaglambackend.DTO;

import org.example.chromaglambackend.DAO.User;
import org.example.chromaglambackend.cg_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }


   @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest user) {
        userService.registerUser(user.getName(),user.getUsername(), user.getEmail(), user.getPassword());
        //return ResponseEntity.ok("User registered successfully.");
       return ResponseEntity.ok("User registered successfully");
    }

    @PutMapping("/id")
    public ResponseEntity<String> updateUser(@RequestBody User user)
    {
        try
        {
            User updated = userService.update(user.getUser_id(), user.getName(), user.getUsername(), user.getPassword(), user.getEmail());
            return ResponseEntity.ok(updated.toString());
        }catch (RuntimeException e)
        {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest user)
    {
        boolean success = userService.authenticate(user.getUsername(), user.getPassword());
        return success ? ResponseEntity.ok("Login successful.") : ResponseEntity.status(401).body("Invalid credentials.");
    }
}
