package ru.job4j.todo.service;

import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

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

    Optional<Task> addTask(User user, Task task);

    void markAsDone(User user, Task task);

    void updateTask(User user, boolean isDone, Task task);

    void deleteTask(Task task);

    Optional<Task> findTaskById(int taskId);

    List<Task> findAllTasks(int userId);

    List<Task> findNewTasks(int userId);

    List<Task> findFinishedTasks(int userId);

    List<Priority> findAllPriorities();

}
