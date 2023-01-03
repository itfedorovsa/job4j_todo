package ru.job4j.todo.repository;

import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Optional;

/**
 * Priority repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.01.23
 */
public interface PriorityRepository {

    Optional<Priority> getPriorityById(int priorityId);

    List<Priority> findAllPriorities();

}
