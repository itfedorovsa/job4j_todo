package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.List;
import java.util.Map;

/**
 * Hibernate category repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.01.23
 */
@Repository
@AllArgsConstructor
@ThreadSafe
public class HibernateCategoryRepository implements CategoryRepository {
    private final CrudRepository crudRepository;

    /**
     * Find categories by id/ids
     *
     * @param ids list of categories' id/ids
     * @return list of categories
     */
    @Override
    public List<Category> findCategoriesByIds(List<Integer> ids) {
        return crudRepository.query(
                "FROM Category WHERE id IN (:cIds)",
                Category.class,
                Map.of("cIds", ids));
    }

    /**
     * Find all categories
     *
     * @return List of all categories
     */
    @Override
    public List<Category> findAllCategories() {
        return crudRepository.query("FROM Category", Category.class);
    }

}
