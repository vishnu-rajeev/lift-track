package com.vishnurajeev.lifttrack.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {

        User user = userService.register(request);
        return new UserResponse(user.getId(), user.getEmail(), user.getCreatedAt());
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest request) {

        User user = userService.authenticate(request);
        return new UserResponse(user.getId(), user.getEmail(), user.getCreatedAt());
    }
}
