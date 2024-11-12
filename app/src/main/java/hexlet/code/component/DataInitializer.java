package hexlet.code.component;

import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public final class DataInitializer implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * Run the initializer to populate default data.
     *
     * @param args The application arguments.
     * @throws Exception If an error occurs during initialization.
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        User defaultUser = userRepository.findByEmail("hexlet@example.com").orElse(null);
        if (defaultUser != null) {
            return;
        }

        User user = new User();
        user.setEmail("hexlet@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword(passwordEncoder.encode("qwerty"));
        userRepository.save(user);
    }
}
