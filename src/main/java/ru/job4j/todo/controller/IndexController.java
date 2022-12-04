package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.service.TaskService;

/**
 * Index Controller
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 27.11.22
 */
@Controller
@ThreadSafe
public class IndexController {
    private final TaskService taskService;

    public IndexController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Index page
     *
     * @param model Model
     * @return index.html - start page
     */
    @GetMapping("/index")
    public String index(Model model) {
        //model.addAttribute("allTasks", taskService.findAllTasks());
        return "index";
    }
}
