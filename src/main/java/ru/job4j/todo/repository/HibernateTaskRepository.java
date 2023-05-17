package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Hibernate Task repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 27.11.22
 */
@Repository
@AllArgsConstructor
@ThreadSafe
public class HibernateTaskRepository implements TaskRepository {

    private static final String FIND_ALL_TASKS = """
            SELECT DISTINCT t FROM Task t JOIN FETCH t.priority JOIN FETCH t.categories
            WHERE user_id = :uId ORDER BY description ASC
            """;

    private static final String FIND_TASK_BY_ID = """
            SELECT DISTINCT t FROM Task t JOIN FETCH t.priority JOIN FETCH t.categories
            WHERE t.id = :uId
            """;

    private static final String FIND_NEW_TASKS = """
            SELECT DISTINCT t FROM Task t JOIN FETCH t.priority JOIN FETCH t.categories
            WHERE isDone = false AND user_id = :uId ORDER BY description ASC
            """;

    private static final String FIND_FINISHED_TASKS = """
            SELECT DISTINCT t FROM Task t JOIN FETCH t.priority JOIN FETCH t.categories
            WHERE isDone = true AND user_id = :uId ORDER BY description ASC
            """;

    /**
     * CrudRepository
     */
    private final CrudRepository crudRepository;

    /**
     * Save Task in DB
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
     * Update Task in DB
     *
     * @param task Task
     */
    @Override
    public void updateTask(Task task) {
        crudRepository.run(session -> session.update(task));
    }

    /**
     * Delete Task from BD
     *
     * @param task Task
     */
    @Override
    public void deleteTask(Task task) {
        crudRepository.run(session -> session.delete(task));
    }

    /**
     * Find all Task
     *
     * @param userId User id
     * @return List of Task
     */
    @Override
    public List<Task> findAllTasks(int userId) {
        return crudRepository.query(FIND_ALL_TASKS, Task.class, Map.of("uId", userId));
    }

    /**
     * Find Task by id
     *
     * @param taskId Task id
     * @return Optional of found Task
     */
    @Override
    public Optional<Task> findTaskById(int taskId) {
        return crudRepository.optional(FIND_TASK_BY_ID, Task.class, Map.of("uId", taskId));
    }

    /**
     * Find list of new Task
     *
     * @param userId User id
     * @return List of new Task
     */
    @Override
    public List<Task> findNewTasks(int userId) {
        return crudRepository.query(FIND_NEW_TASKS, Task.class, Map.of("uId", userId));
    }

    /**
     * Find list of finished Task
     *
     * @param userId User id
     * @return List of finished Task
     */
    @Override
    public List<Task> findFinishedTasks(int userId) {
        return crudRepository.query(FIND_FINISHED_TASKS, Task.class, Map.of("uId", userId));
    }

}