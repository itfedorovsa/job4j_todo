package ru.job4j.todo.service;

import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

/**
 *  User service interface
 *  @author itfedorovsa (itfedorovsa@gmail.com)
 *  @since 06.12.22
 *  @version 1.0
 */
public interface UserService {

    Optional<User> add(User user);

    void update(User user);

    List<User> findAll();

    Optional<User> findById(Integer id);

    Optional<User> findByLogin(String login, String password);

}
