package ru.job4j.todo.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Postgres User repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 06.12.22
 */
@ThreadSafe
@Repository
public class PostgresUserRepository implements UserRepository {

    @Override
    public Optional<User> add(User user) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
