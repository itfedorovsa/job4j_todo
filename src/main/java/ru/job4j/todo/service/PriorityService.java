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

    Optional<Priority> getPriorityById(int priorityId);

    List<Priority> findAllPriorities();

}
