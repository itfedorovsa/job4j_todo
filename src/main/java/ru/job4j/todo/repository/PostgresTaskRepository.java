package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  Postgres task repository
 *  @author itfedorovsa (itfedorovsa@gmail.com)
 *  @since 27.11.22
 *  @version 1.0
 */
@Repository
@ThreadSafe
@AllArgsConstructor
public class PostgresTaskRepository implements TaskRepository {
    private final SessionFactory sf;

    @Override
    public Optional<Task> addTask(Task task) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> editTask(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> deleteTask(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAllTasks() {
        return null;
    }

    @Override
    public List<Task> findNewTasks() {
        return null;
    }

    @Override
    public List<Task> findFinishedTasks() {
        return null;
    }

}