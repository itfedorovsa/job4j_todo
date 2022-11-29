package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.service.TaskService;

/**
 *  Task Controller
 *  @author itfedorovsa (itfedorovsa@gmail.com)
 *  @since 27.11.22
 *  @version 1.0
 */
@Controller
@ThreadSafe
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * All tasks page
     * @param model Model
     * @return allTasks.html - all tasks from list
     */
    @GetMapping("/allTasks")
    public String allTasks(Model model) {
        model.addAttribute("allTasks", taskService.findAllTasks());
        return "task/allTasks";
    }

    /**
     * New tasks page
     * @param model Model
     * @return newTasks.html - new (opened) tasks from list
     */
    @GetMapping("/newTasks")
    public String newTasks(Model model) {
        model.addAttribute("newTasks", taskService.findNewTasks());
        return "task/newTasks";
    }

    /**
     * Finished tasks page
     * @param model Model
     * @return finishedTasks.html - finished (closed) tasks from list
     */
    @GetMapping("/finishedTasks")
    public String finishedTasks(Model model) {
        model.addAttribute("finishedTasks", taskService.findFinishedTasks());
        return "task/finishedTasks";
    }
}
