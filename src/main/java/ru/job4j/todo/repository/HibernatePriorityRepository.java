package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Hibernate Priority repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.01.23
 */
@Repository
@AllArgsConstructor
@ThreadSafe
public class HibernatePriorityRepository implements PriorityRepository {

    private static final String FIND_PRIORITY_BY_ID = "FROM Priority WHERE id = :pId";

    private static final String FIND_ALL_PRIORITIES = "FROM Priority";

    /**
     * CrudRepository
     */
    private final CrudRepository crudRepository;

    /**
     * Find Priority by id
     *
     * @param priorityId Priority id
     * @return Optional of Priority or empty Optional
     */
    @Override
    public Optional<Priority> getPriorityById(int priorityId) {
        return crudRepository.optional(
                FIND_PRIORITY_BY_ID,
                Priority.class,
                Map.of("pId", priorityId));
    }

    /**
     * Find all Priority
     *
     * @return List of Priority
     */
    @Override
    public List<Priority> findAllPriorities() {
        return crudRepository.query(FIND_ALL_PRIORITIES, Priority.class);
    }

}
