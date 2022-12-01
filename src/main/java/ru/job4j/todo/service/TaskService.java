package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

/**
 *  Task service interface
 *  @author itfedorovsa (itfedorovsa@gmail.com)
 *  @since 27.11.22
 *  @version 1.0
 */
@Service
@ThreadSafe
public interface TaskService {

    Optional<Task> addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);

    Optional<Task> findTaskById(Integer id);

    List<Task> findAllTasks();

    List<Task> findNewTasks();

    List<Task> findFinishedTasks();

}
