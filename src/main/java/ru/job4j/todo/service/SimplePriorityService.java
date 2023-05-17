package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.repository.PriorityRepository;

import java.util.List;
import java.util.Optional;

/**
 * Priority service layer
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.01.23
 */
@Service
@AllArgsConstructor
@ThreadSafe
public class SimplePriorityService implements PriorityService {

    /**
     * PriorityRepository implementation
     */
    private final PriorityRepository priorityRepository;

    /**
     * Find Priority by id
     *
     * @param priorityId Priority id
     * @return Optional of Priority or empty Optional
     */
    @Override
    public Optional<Priority> getPriorityById(int priorityId) {
        return priorityRepository.getPriorityById(priorityId);
    }

    /**
     * Find all Priority
     *
     * @return List of Priority
     */
    @Override
    public List<Priority> findAllPriorities() {
        return priorityRepository.findAllPriorities();
    }
}
