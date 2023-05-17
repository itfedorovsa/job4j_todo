package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

/**
 * Task service layer
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 27.11.22
 */
@Service
@AllArgsConstructor
@ThreadSafe
public class SimpleTaskService implements TaskService {

    /**
     * TaskRepository implementation
     */
    private final TaskRepository taskRepository;

    /**
     * Save Task in DB
     *
     * @param task Task
     * @return Optional of Task with added id
     */
    @Override
    public Optional<Task> addTask(User user, Task task) {
        task.setUser(user);
        return taskRepository.addTask(task);
    }

    /**
     * Mark task as done
     *
     * @param user       User
     * @param task       Task
     * @param priority   Priority
     * @param categories List of Category
     */
    @Override
    public void markAsDone(User user, Task task, Priority priority, List<Category> categories) {
        task.setUser(user);
        task.setDone(true);
        task.setPriority(priority);
        task.setCategories(categories);
        taskRepository.updateTask(task);
    }

    /**
     * Update Task in DB
     *
     * @param task Task
     */
    @Override
    public void updateTask(User user, boolean isDone, Task task) {
        task.setUser(user);
        task.setDone(isDone);
        taskRepository.updateTask(task);
    }

    /**
     * Delete Task from BD
     *
     * @param task Task
     */
    @Override
    public void deleteTask(Task task) {
        taskRepository.deleteTask(task);
    }

    /**
     * Find all Task
     *
     * @param userId User id
     * @return List of Task
     */
    @Override
    public List<Task> findAllTasks(int userId) {
        return taskRepository.findAllTasks(userId);
    }

    /**
     * Find Task by id
     *
     * @param taskId Task id
     * @return Optional of found Task
     */
    @Override
    public Optional<Task> findTaskById(int taskId) {
        return taskRepository.findTaskById(taskId);
    }

    /**
     * Find list of new Task
     *
     * @param userId User id
     * @return List of new Task
     */
    @Override
    public List<Task> findNewTasks(int userId) {
        return taskRepository.findNewTasks(userId);
    }

    /**
     * Find list of finished Task
     *
     * @param userId User id
     * @return List of finished Task
     */
    @Override
    public List<Task> findFinishedTasks(int userId) {
        return taskRepository.findFinishedTasks(userId);
    }

}

