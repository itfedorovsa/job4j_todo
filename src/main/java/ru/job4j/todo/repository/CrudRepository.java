package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This class implements analogue of Command pattern
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 13.12.22
 */
@Repository
@AllArgsConstructor
@ThreadSafe
public class CrudRepository {

    private final SessionFactory sf;

    /**
     * Run command without arguments
     *
     * @param command A command in the form of a function for use as tx() method parameter
     */
    public void run(Consumer<Session> command) {
        tx(session -> {
                    command.accept(session);
                    return null;
                }
        );
    }

    /**
     * Create and run command with arguments
     *
     * @param query Sql/hql query
     * @param args  Arguments to be inserted into the query
     */
    public void run(String query, Map<String, Object> args) {
        Consumer<Session> command = session -> {
            var sq = session
                    .createQuery(query);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            sq.executeUpdate();
        };
        run(command);
    }

    /**
     * Create and run command
     *
     * @param query Sql/hql query
     * @param cl    Classname.Class
     * @param args  Arguments to be inserted into the query
     * @param <T>   Used generic data type
     * @return Command object
     */
    public <T> Optional<T> optional(String query, Class<T> cl, Map<String, Object> args) {
        Function<Session, Optional<T>> command = session -> {
            var sq = session
                    .createQuery(query, cl);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            return Optional.ofNullable(sq.getSingleResult());
        };
        return tx(command);
    }

    /**
     * Create and run command without arguments
     *
     * @param query Sql/hql query
     * @param cl    Classname.Class
     * @param <T>   Used generic data type
     * @return List of queried objects
     */
    public <T> List<T> query(String query, Class<T> cl) {
        Function<Session, List<T>> command = session -> session
                .createQuery(query, cl)
                .list();
        return tx(command);
    }

    /**
     * Create and run command with arguments
     *
     * @param query Sql/hql query
     * @param cl    Classname.Class
     * @param args  Arguments to be inserted into the query
     * @param <T>   Used generic data type
     * @return List of queried T objects
     */
    public <T> List<T> query(String query, Class<T> cl, Map<String, Object> args) {
        Function<Session, List<T>> command = session -> {
            var sq = session
                    .createQuery(query, cl);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            return sq.list();
        };
        return tx(command);
    }

    /**
     * Primary method. Runs abstract command
     *
     * @param command A command to apply in the form of a function
     * @param <T>     Used generic data type
     * @return Object T
     */
    public <T> T tx(Function<Session, T> command) {
        var session = sf.openSession();
        try (session) {
            var tx = session.beginTransaction();
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (Exception e) {
            var tx = session.getTransaction();
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
}
