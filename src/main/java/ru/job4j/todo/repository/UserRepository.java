package ru.job4j.todo.repository;

import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

/**
 * User repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 06.12.22
 */
public interface UserRepository {

    Optional<User> add(User user);

    void update(User user);

    List<User> findAll();

    Optional<User> findById(Integer id);

    Optional<User> findByLoginAndPassword(String login, String password);

}
