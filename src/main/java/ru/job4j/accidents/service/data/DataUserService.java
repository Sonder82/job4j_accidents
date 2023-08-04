package ru.job4j.accidents.service.data;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.data.UserRepository;
import ru.job4j.accidents.service.UserService;

import java.util.Optional;

@Primary
@Service
@AllArgsConstructor
public class DataUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> save(User user) {
        userRepository.save(user);
        return Optional.of(user);
    }
}
