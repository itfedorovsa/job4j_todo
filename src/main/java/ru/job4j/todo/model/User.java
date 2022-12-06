package ru.job4j.todo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * User model
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 06.12.22
 */
@Entity
@Table(name = "todo_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String login;
    private String password;
}
