package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

@Service
@ThreadSafe
public interface UserService {

    Optional<User> add(User user);

    void update(User user);

    Optional<User> findById(Integer id);

    Optional<User> findByLogin(String login);

    List<User> findAll();
}
