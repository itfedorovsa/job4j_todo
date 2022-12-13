package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
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
    public Optional<Task> addTask(Task task) {
        return store.addTask(task);
    }

    public void markAsDone(Task task) {
        task.setDone(true);
        store.updateTask(task);
    }

    @Override
    public void updateTask(Task task) {
        store.updateTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        store.deleteTask(task);
    }

    @Override
    public Optional<Task> findTaskById(Integer id) {
        return store.findTaskById(id);
    }

    @Override
    public List<Task> findAllTasks() {
        return store.findAllTasks();
    }

    @Override
    public List<Task> findNewTasks() {
        return store.findNewTasks();
    }

    @Override
    public List<Task> findFinishedTasks() {
        return store.findFinishedTasks();
    }

}

