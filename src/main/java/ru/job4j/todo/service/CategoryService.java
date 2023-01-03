package ru.job4j.todo.service;

import ru.job4j.todo.model.Category;

import java.util.List;

/**
 * Category service interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.01.23
 */
public interface CategoryService {

    List<Category> findCategoriesByIds(List<Integer> ids);

    List<Category> findAllCategories();

}
