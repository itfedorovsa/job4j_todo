package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.CategoryRepository;

import java.util.List;

/**
 * Category service layer
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.01.23
 */
@Service
@AllArgsConstructor
@ThreadSafe
public class SimpleCategoryService implements CategoryService {

    private final CategoryRepository store;

    /**
     * Find list of Category by id/ids
     *
     * @param ids list of Category id/ids
     * @return list of Category
     */
    @Override
    public List<Category> findCategoriesByIds(List<Integer> ids) {
        return store.findCategoriesByIds(ids);
    }

    /**
     * Find all Category
     *
     * @return List of Category
     */
    @Override
    public List<Category> findAllCategories() {
        return store.findAllCategories();
    }

}
