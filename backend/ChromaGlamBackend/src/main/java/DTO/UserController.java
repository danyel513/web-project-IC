package DTO;

import DAO.User;
import cg_service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }


   @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerUser(user.getName(),user.getUsername(), user.getPassword(), user.getEmail());
        return ResponseEntity.ok("User registered successfully.");
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
    public ResponseEntity<String> login(@RequestBody User user)
    {
        boolean success = userService.authenticate(user.getUsername(), user.getPassword());
        return success ? ResponseEntity.ok("Login successful.") : ResponseEntity.status(401).body("Invalid credentials.");
    }
}
