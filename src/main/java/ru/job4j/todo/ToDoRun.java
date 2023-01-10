package ru.job4j.todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.model.Task;

import java.util.List;

/**
 * Checking lazy loading class (Temporary)
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 20.12.22
 */
public class ToDoRun {

    public static void main(String[] args) {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            var stored = listOf("FROM Task f JOIN FETCH f.priority", Task.class, sf);
            for (Task task : stored) {
                System.out.println(task.getPriority());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static <T> List<T> listOf(String query, Class<T> model, SessionFactory sf) {
        Session session = sf.openSession();
        var rsl = session.createQuery(query, model)
                .getResultList();
        session.close();
        return rsl;
    }

}