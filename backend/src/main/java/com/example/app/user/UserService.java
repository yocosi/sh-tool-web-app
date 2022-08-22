// This class is a Service.
// Services are used to write business logic in a different layer, separated from Controller class file.
// All the methods for the endpoints are implemented here and are called by the Controller class.
package com.example.app.user;

import com.example.app.registration.token.ConfirmationToken;
import com.example.app.registration.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service // For the dependency injection of this class
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final static String USER_NOT_FOUND_MSG = "error: user with email %s not found";

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user){
        boolean exists = userRepository.findUserByEmail(user.getEmail()).isPresent();
        if (exists){
            // TODO: check if atributes are the same and
            // TODO: if email not confirmed send confirmation email
            throw new IllegalStateException("error: email already taken.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO : Send email
        return token;
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
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
