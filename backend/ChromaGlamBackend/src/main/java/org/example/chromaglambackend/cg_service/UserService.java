package org.example.chromaglambackend.cg_service;

import org.example.chromaglambackend.DAO.User;
import org.example.chromaglambackend.DAO.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service //singleton
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void registerUser(String name, String username,String email, String password)
    {
        String hashedPassword = PasswordEncryption.hashPassword(password);
        User user = new User(name,username,email,hashedPassword);
        userRepository.save(user);
    }

    public User update(long id, String name, String username, String email, String password)
    {
        Optional<User> userUp = userRepository.findById(id);
        if(userUp.isPresent())
        {
            User user = userUp.get();
            user.setName(name);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(PasswordEncryption.hashPassword(password));
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }

    public void deleteUser(long id)
    {
        userRepository.deleteById(id);
    }

    public boolean authenticate(String email, String password)
    {
        User user = userRepository.findByEmail(email);
        if(user == null) return false;
        return PasswordEncryption.checkPassword(password, user.getPassword());
    }
}
