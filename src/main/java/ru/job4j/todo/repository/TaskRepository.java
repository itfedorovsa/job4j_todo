package ru.job4j.todo.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

/**
 *  Task repository interface
 *  @author itfedorovsa (itfedorovsa@gmail.com)
 *  @since 27.11.22
 *  @version 1.0
 */
@Repository
@ThreadSafe
public interface TaskRepository {

    Optional<Task> addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);

    Optional<Task> findTaskById(Integer id);

    List<Task> findAllTasks();

    List<Task> findNewTasks();

    List<Task> findFinishedTasks();

}
