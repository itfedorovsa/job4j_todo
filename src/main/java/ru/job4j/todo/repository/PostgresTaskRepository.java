package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  Postgres task repository
 *  @author itfedorovsa (itfedorovsa@gmail.com)
 *  @since 27.11.22
 *  @version 1.0
 */
@Repository
@ThreadSafe
@AllArgsConstructor
public class PostgresTaskRepository implements TaskRepository {
    private final SessionFactory sf;

    @Override
    public Optional<Task> addTask(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.of(task);
    }

    @Override
    public void updateTask(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteTask(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Task> findAllTasks() {
        Session session = sf.openSession();
        List<Task> tasks = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Task> query = session.createQuery("FROM Task", Task.class);
            tasks = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return tasks;
    }

    @Override
    public Optional<Task> findTaskById(Integer id) {
        Session session = sf.openSession();
        Optional<Task> task = Optional.empty();
        try {
            session.beginTransaction();
            Query<Task> query = session.createQuery("FROM Task WHERE id = :uId", Task.class);
            query.setParameter("uId", id);
            task = Optional.of(query.uniqueResult());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public List<Task> findNewTasks() {
        String request = "FROM Task WHERE isDone = false";
        return findSpecifiedTasks(request);
    }

    @Override
    public List<Task> findFinishedTasks() {
        String request = "FROM Task WHERE isDone = true";
        return findSpecifiedTasks(request);
    }

    /**
     * Standalone method to get a list of new or finished tasks
     * @param request sql-query with true or false "isDone" parameter
     * @return list of new or finished tasks
     */
    private List<Task> findSpecifiedTasks(String request) {
        Session session = sf.openSession();
        List<Task> tasks = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Task> query = session.createQuery(request, Task.class);
            tasks = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return tasks;
    }

}


