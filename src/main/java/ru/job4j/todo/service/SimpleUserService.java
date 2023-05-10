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

    /**
     * Save User in DB
     *
     * @param user User
     * @return Optional of User with added id
     */
    @Override
    public Optional<User> add(User user) {
        return store.add(user);
    }

    /**
     * Update User in DB
     *
     * @param user User
     */
    @Override
    public void update(User user) {
        store.update(user);
    }

    /**
     * Find User by login and password
     *
     * @param login    Login
     * @param password Password
     * @return Optional of User
     */
    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return store.findByLoginAndPassword(login, password);
    }

}
