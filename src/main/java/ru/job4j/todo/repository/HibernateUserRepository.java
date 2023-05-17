package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

/**
 * Hibernate User repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 06.12.22
 */
@Repository
@AllArgsConstructor
@ThreadSafe
public class HibernateUserRepository implements UserRepository {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "FROM User WHERE login = :uLogin AND password = :uPass";

    /**
     * CrudRepository
     */
    private final CrudRepository crudRepository;

    /**
     * Save User in DB
     *
     * @param user User
     * @return Optional of User with added id
     */
    @Override
    public Optional<User> add(User user) {
        try {
            crudRepository.run(session -> session.save(user));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return user.getId() == 0 ? Optional.empty() : Optional.of(user);
    }

    /**
     * Update User in DB
     *
     * @param user User
     */
    @Override
    public void update(User user) {
        crudRepository.run(session -> session.update(user));
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
        Optional<User> user = Optional.empty();
        try {
            user = crudRepository.optional(
                    FIND_BY_LOGIN_AND_PASSWORD,
                    User.class,
                    Map.of("uLogin", login, "uPass", password));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return user;
    }

}
