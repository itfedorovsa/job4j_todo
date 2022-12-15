package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

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

    void markAsDone(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);

    Optional<Task> findTaskById(Integer id);

    List<Task> findAllTasks(Integer id);

    List<Task> findNewTasks();

    List<Task> findFinishedTasks();

}
