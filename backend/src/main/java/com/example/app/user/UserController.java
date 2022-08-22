// This class is a controller.
// A controller is responsible for handling request from the clients and providing them with the responses as JSON/XML in return.
// Here we write our different HTTP endpoints.
// And then call, in every method, the corresponding Service who takes care of the business logic.
package com.example.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired // Dependency Injection of Pokemon Service. To be able to use it
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUser(){
        return userService.getUser();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){ // We take the request body and map it into the user
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{usersId}")
    public void deleteUser(@PathVariable("usersId") Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{usersId}")
    public void updateUser(@PathVariable("usersId") Long userId,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String email){
        userService.updateUser(userId, username, email);
    }
}
