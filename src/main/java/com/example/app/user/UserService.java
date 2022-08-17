package com.example.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service // For the dependency injection of this class
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("email already exists");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("User with id " + userId + " does not exists");
        }
        userRepository.deleteById(userId);
    }

    // With transactional annotation this is not necessary to implement jpql query
    // Because with this annotation the entity goes into a managed state
    @Transactional
    public void updateUser(Long userId, String username, String email) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exists"));

        if(username != null && username.length() > 0 && !Objects.equals(user.getUsername(), username)){
            user.setUsername(username);
        }

        if(email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)){
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("email already exists");
            }
            user.setEmail(email);
        }
    }
}
