// This class is a controller.
// A controller is responsible for handling request from the clients and providing them with the responses as JSON/XML in return.
// Here we write our different HTTP endpoints.
// And then call, in every method, the corresponding Service who takes care of the business logic.
package com.example.app.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/registration")
@CrossOrigin(origins = "*")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
