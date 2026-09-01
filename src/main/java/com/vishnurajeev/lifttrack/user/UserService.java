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
            throw new DuplicateEmailException(request.email());
        }

        String passwordHash = passwordEncoder.encode(request.password());

        User user = new User(request.email(), passwordHash);

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User authenticate(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        boolean passwordMatches = passwordEncoder.matches(request.password(), user.getPasswordHash());

        if (!passwordMatches) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return user;
    }
}
