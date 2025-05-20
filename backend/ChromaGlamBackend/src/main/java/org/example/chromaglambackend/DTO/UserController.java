package org.example.chromaglambackend.DTO;

import org.example.chromaglambackend.cg_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private final UserService userService;

    // constructor -> initialize the userService
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    // register method will create a new user and will return the
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<HttpStatus> register(@RequestBody RegisterRequest user)
    {
        // register user return true if the created user in database equals the user input
        boolean response = userService.registerUser(user.getName(),user.getUsername(), user.getEmail(), user.getPassword(), user.getPreferences());
        if(response) return ResponseEntity.ok(HttpStatus.CREATED); // return created status
        else return ResponseEntity.ok(HttpStatus.CONFLICT); // return conflict if the user already exists
    }

    // delete will remove an user
    @DeleteMapping(value = "/delete",  consumes = "application/json")
    public ResponseEntity<HttpStatus> delete(@RequestBody String username)
    {
        boolean r = userService.deleteUser(username); // call delete function to erase data from database
        if (!r) return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(HttpStatus.FOUND); // return the found status to signal deletion completed
    }

    // log in will authenticate a user
    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<HttpStatus> login(@RequestBody LoginRequest user)
    {
        boolean success = userService.authenticate(user.getUsername(), user.getPassword()); // this will check for email and password in the database
        return success ? ResponseEntity.ok(HttpStatus.FOUND) : ResponseEntity.ok(HttpStatus.NOT_FOUND); // return the state
    }

//    // UNUSED (to be verified and corrected
//    @PutMapping(value = "/update", consumes = "application/json")
//    public ResponseEntity<String> updateUser(@RequestBody User user)
//    {
//        try
//        {
//            User updated = userService.update(user.getUser_id(), user.getName(), user.getUsername(), user.getPassword(), user.getEmail());
//            return ResponseEntity.ok(updated.toString());
//        }catch (RuntimeException e)
//        {
//            return ResponseEntity.status(404).body("User not found.");
//        }
//    }
}
