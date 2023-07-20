package application.services;

import application.models.User;
import application.models.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User getUserByUsername(String email){
        User user = userRepository.findUserByUsername(email);
        return user;
    }
    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }
    public User createUser(User user){
        User newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
    }

    public void saveUser(User user){
        userRepository.save(user);
        userRepository.flush();
    }
    public User getUserByID(Long id){
        return userRepository.getReferenceById(id);
    }
}