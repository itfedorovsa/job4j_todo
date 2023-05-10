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

    /**
     * Save Task in DB
     *
     * @param task Task
     * @return Optional of Task with added id
     */
    Optional<Task> addTask(Task task);

    /**
     * Update Task in DB
     *
     * @param task Task
     */
    void updateTask(Task task);

    /**
     * Delete Task from BD
     *
     * @param task Task
     */
    void deleteTask(Task task);

    /**
     * Find all Task
     *
     * @param userId User id
     * @return List of Task
     */
    List<Task> findAllTasks(int userId);

    /**
     * Find Task by id
     *
     * @param taskId Task id
     * @return Optional of found Task
     */
    Optional<Task> findTaskById(int taskId);

    /**
     * Find list of new Task
     *
     * @param userId User id
     * @return List of new Task
     */
    List<Task> findNewTasks(int userId);

    /**
     * Find list of finished Task
     *
     * @param userId User id
     * @return List of finished Task
     */
    List<Task> findFinishedTasks(int userId);

}
