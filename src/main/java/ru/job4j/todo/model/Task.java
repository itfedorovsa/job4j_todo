package ru.job4j.todo.model;

import lombok.Data;
import java.time.LocalDateTime;

/**
 *  Task model
 *  @author itfedorovsa (itfedorovsa@gmail.com)
 *  @since 27.11.22
 *  @version 1.0
 */
@Data
public class Task {
    private Integer id;
    private String description;
    private LocalDateTime created = LocalDateTime.now();
    boolean isDone;
}
