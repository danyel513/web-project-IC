package cg_service;

import DAO.User;
import DAO.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService
{
    private UserRepository userRepository;

    public User registerUser(String name, String username,String email, String password)
    {
        String hashedPassword = PasswordEncryption.hashPassword(password);
        User user = new User();
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
