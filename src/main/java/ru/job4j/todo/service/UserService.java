package ru.job4j.todo.service;

import ru.job4j.todo.model.User;

import java.util.Optional;

/**
 * User service interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 06.12.22
 */
public interface UserService {

    Optional<User> add(User user);

    void update(User user);

    Optional<User> findByLoginAndPassword(String login, String password);

}
