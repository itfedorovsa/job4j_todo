package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Hibernate task repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 27.11.22
 */
@Repository
@AllArgsConstructor
@ThreadSafe
public class HibernateTaskRepository implements TaskRepository {
    private final CrudRepository crudRepository;

    /**
     * Save task in DB
     *
     * @param task Task
     * @return Optional of Task with added id
     */
    @Override
    public Optional<Task> addTask(Task task) {
        crudRepository.run(session -> session.save(task));
        return Optional.of(task);
    }

    /**
     * Update task in DB
     *
     * @param task Task
     */
    @Override
    public void updateTask(Task task) {
        crudRepository.run(session -> session.update(task));
    }

    /**
     * Delete task from BD
     *
     * @param task Task
     */
    @Override
    public void deleteTask(Task task) {
        crudRepository.run(session -> session.delete(task));
    }

    /**
     * Find all tasks
     *
     * @param userId User id
     * @return List with all tasks
     */
    @Override
    public List<Task> findAllTasks(int userId) {
        return crudRepository.query(
                "FROM Task WHERE user_id = :uId ORDER BY description ASC",
                Task.class,
                Map.of("uId", userId));
    }

    /**
     * Find task by id
     *
     * @param taskId Task id
     * @return Optional of found task
     */
    @Override
    public Optional<Task> findTaskById(int taskId) {
        return crudRepository.optional("FROM Task WHERE id = :uId",
                Task.class,
                Map.of("uId", taskId));
    }

    /**
     * Find new tasks
     *
     * @param userId User id
     * @return List of new tasks
     */
    @Override
    public List<Task> findNewTasks(int userId) {
        return crudRepository.query("FROM Task WHERE isDone = false AND user_id = :uId ORDER BY description ASC",
                Task.class,
                Map.of("uId", userId));
    }

    /**
     * Find finished tasks
     * @param userId User id
     * @return List of finished tasks
     */
    @Override
    public List<Task> findFinishedTasks(int userId) {
        return crudRepository.query("FROM Task WHERE isDone = true AND user_id = :uId ORDER BY description ASC",
                Task.class,
                Map.of("uId", userId));
    }

}