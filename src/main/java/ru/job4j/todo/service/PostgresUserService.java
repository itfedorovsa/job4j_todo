package ru.job4j.todo.service;

import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class PostgresUserService implements UserRepository {

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
