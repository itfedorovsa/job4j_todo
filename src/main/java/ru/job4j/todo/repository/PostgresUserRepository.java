package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Postgres User repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 06.12.22
 */
@Repository
@AllArgsConstructor
@ThreadSafe
public class PostgresUserRepository implements UserRepository {
    private final SessionFactory sf;

    @Override
    public Optional<User> add(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.of(user);
    }

    @Override
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> findAll() {
        Session session = sf.openSession();
        List<User> users = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User", User.class);
            users = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public Optional<User> findById(Integer id) {
        Session session = sf.openSession();
        Optional<User> user = Optional.empty();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE id = :uId", User.class);
            query.setParameter("uId", id);
            user = Optional.of(query.uniqueResult());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        Session session = sf.openSession();
        Optional<User> user = Optional.empty();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE login = :uLogin AND password = :uPass", User.class);
            query.setParameter("uLogin", login);
            query.setParameter("uPass", password);
            user = Optional.of(query.uniqueResult());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

}
