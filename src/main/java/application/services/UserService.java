package application.services;

import application.models.Relationship;
import application.models.RelationshipRepository;
import application.models.User;
import application.models.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final RelationshipRepository relationshipRepository;
    public UserService(UserRepository userRepository, RelationshipRepository relationshipRepository){
        this.userRepository = userRepository;
        this.relationshipRepository = relationshipRepository;
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
        Relationship relationship = new Relationship(null, newUser.getId(), newUser.getId());
        return newUser;
    }

    public void saveUser(User user){
        userRepository.save(user);
        userRepository.flush();
    }
    public User getUserByID(Long id){
        return userRepository.getReferenceById(id);
    }
    public List<User> getAllNotContacts(Long id){
        return userRepository.getAllNotContacts(id);
    }

    public List<User> getAllContacts(Long id){
        return userRepository.getAllContacts(id);
    }

    public void addRelation(Long id1, Long id2){
        Relationship relationship = new Relationship(null, id1, id2);
        relationshipRepository.save(relationship);
    }
    public void deleteRelation(Long id1, Long id2){
        Relationship relationship = relationshipRepository.findRelationshipByIDs(id1, id2);
        relationshipRepository.delete(relationship);
    }

    public Relationship getRelationship(Long id1, Long id2){
        return relationshipRepository.findRelationshipByIDs(id1, id2);
    }

}