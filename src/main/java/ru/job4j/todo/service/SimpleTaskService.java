package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
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
    private final TaskRepository store;

    @Override
    public Optional<Task> addTask(User user, Task task) {
        task.setUser(user);
        return store.addTask(task);
    }

    @Override
    public void markAsDone(User user, Task task) {
        task.setUser(user);
        task.setDone(true);
        store.updateTask(task);
    }

    @Override
    public void updateTask(User user, boolean isDone, Task task) {
        task.setDone(isDone);
        task.setUser(user);
        store.updateTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        store.deleteTask(task);
    }

    @Override
    public Optional<Task> findTaskById(int taskId) {
        return store.findTaskById(taskId);
    }

    @Override
    public List<Task> findAllTasks(int userId) {
        return store.findAllTasks(userId);
    }

    @Override
    public List<Task> findNewTasks(int userId) {
        return store.findNewTasks(userId);
    }

    @Override
    public List<Task> findFinishedTasks(int userId) {
        return store.findFinishedTasks(userId);
    }

}

