package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Task repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 27.11.22
 */
public interface TaskRepository {

    Optional<Task> addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);

    Optional<Task> findTaskById(int taskId);

    List<Task> findAllTasks(int userId);

    List<Task> findNewTasks(int userId);

    List<Task> findFinishedTasks(int userId);

}
