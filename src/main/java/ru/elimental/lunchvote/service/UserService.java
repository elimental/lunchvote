package ru.elimental.lunchvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.elimental.lunchvote.exception.UserAlreadyExistsException;
import ru.elimental.lunchvote.model.User;
import ru.elimental.lunchvote.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        User existingUser = userRepository.findByLogin(user.getLogin());
        if (existingUser != null) {
            throw new UserAlreadyExistsException();
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
