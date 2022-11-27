package ru.job4j.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;

/**
 *  Postgres task repository
 *  @author itfedorovsa (itfedorovsa@gmail.com)
 *  @since 27.11.22
 *  @version 1.0
 */
@Repository
@ThreadSafe
public class PostgresTaskRepository implements TaskRepository {
}
