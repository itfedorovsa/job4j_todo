package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@ThreadSafe
public class PostgresUserService implements UserService {
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
    public List<User> findAll() {
        return store.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return store.findById(id);
    }

    @Override
    public Optional<User> findByLogin(String login, String password) {
        return store.findByLoginAndPassword(login, password);
    }

}
