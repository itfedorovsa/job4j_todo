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

    List<Category> findCategoriesByIds(List<Integer> ids);

    List<Category> findAllCategories();

}
