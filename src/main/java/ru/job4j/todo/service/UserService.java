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

    /**
     * Save User in DB
     *
     * @param user User
     * @return Optional of User with added id
     */
    Optional<User> add(User user);

    /**
     * Update User in DB
     *
     * @param user User
     */
    void update(User user);

    /**
     * Find User by login and password
     *
     * @param login    Login
     * @param password Password
     * @return Optional of User
     */
    Optional<User> findByLoginAndPassword(String login, String password);

}
