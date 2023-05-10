package ru.job4j.todo.service;

import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Optional;

/**
 * Priority service interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.01.23
 */
public interface PriorityService {

    /**
     * Find Priority by id
     *
     * @param priorityId Priority id
     * @return Optional of Priority or empty Optional
     */
    Optional<Priority> getPriorityById(int priorityId);

    /**
     * Find all Priority
     *
     * @return List of Priority
     */
    List<Priority> findAllPriorities();

}
