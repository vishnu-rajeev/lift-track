package com.vishnurajeev.lifttrack.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User register(RegisterRequest request) {

        if(userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        String passwordHash = passwordEncoder.encode(request.password());

        User user = new User(request.email(), passwordHash);

        return userRepository.save(user);
    }
}
