package ru.job4j.todo.repository;

import ru.job4j.todo.model.Category;

import java.util.List;

/**
 * Category repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.01.23
 */
public interface CategoryRepository {

    /**
     * Find list of Category by id/ids
     *
     * @param ids list of Category id/ids
     * @return list of Category
     */
    List<Category> findCategoriesByIds(List<Integer> ids);

    /**
     * Find all Category
     *
     * @return List of Category
     */
    List<Category> findAllCategories();

}
