package com.example.app.registration;

import com.example.app.registration.token.ConfirmationToken;
import com.example.app.registration.token.ConfirmationTokenService;
import com.example.app.user.User;
import com.example.app.user.UserRole;
import com.example.app.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request){
        LocalDate lt = LocalDate.now();
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("error: the email is not valid.");
        }
        return userService.signUpUser(new User(request.getUsername(), request.getEmail(), request.getPassword(), lt, UserRole.USER));
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("error: token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("error: email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("error: token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
