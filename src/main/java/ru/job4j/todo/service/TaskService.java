package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * Task service interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 27.11.22
 */
public interface TaskService {

    Optional<Task> addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);

    Optional<Task> findTaskById(Integer id);

    List<Task> findAllTasks();

    List<Task> findNewTasks();

    List<Task> findFinishedTasks();

}
