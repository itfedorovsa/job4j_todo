package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserRepository;

import java.util.Optional;

/**
 * User service layer
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 27.11.22
 */
@Service
@AllArgsConstructor
@ThreadSafe
public class SimpleUserService implements UserService {

    private final UserRepository store;

    @Override
    public Optional<User> add(User user) {
        return store.add(user);
    }

    @Override
    public void update(User user) {
        store.update(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return store.findByLoginAndPassword(login, password);
    }

}
