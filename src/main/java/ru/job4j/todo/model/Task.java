package ru.job4j.todo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Task model
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 27.11.22
 */

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private LocalDateTime created = LocalDateTime.now();
    boolean isDone;
}
