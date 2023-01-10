package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.List;
import java.util.Map;

/**
 * Hibernate Category repository
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

    private static final String FIND_CATEGORY_BY_ID = "FROM Category WHERE id IN (:cIds)";

    private static final String FIND_ALL_CATEGORIES = "FROM Category";

    /**
     * Find categories by id/ids
     *
     * @param ids list of categories' id/ids
     * @return list of categories
     */
    @Override
    public List<Category> findCategoriesByIds(List<Integer> ids) {
        return crudRepository.query(
                FIND_CATEGORY_BY_ID,
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
        return crudRepository.query(FIND_ALL_CATEGORIES, Category.class);
    }

}
