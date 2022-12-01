package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

import javax.swing.text.html.Option;
import java.util.Optional;

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

    /**
     * New task creating page
     * @return newTask.html - new task creating page
     */
    @GetMapping("/newTask")
    public String newTask() {
        return "task/newTask";
    }

    @PostMapping("/formAddTask")
    public String formAddTask(@ModelAttribute Task task) {
        task.setDone(false);
        taskService.addTask(task);
        return "redirect:/allTasks";
    }

    @GetMapping("/formTaskDesc/{taskId}")
    public String formTaskDesc(Model model, @PathVariable("taskId") int id) {
        Optional<Task> taskById = taskService.findTaskById(id);
        Task taskObj = new Task();
        if (taskById.isPresent()) {
            taskObj = taskById.get();
        }
        model.addAttribute("task", taskObj);
        return "task/taskDesc";
    }

    /*@PostMapping("/taskDesc")
    public String taskDesc(Model model, @ModelAttribute Task task) {
                //taskService.updateTask(task);
        return "redirect:/allTasks";
    }*/

    @PostMapping("/taskDesc")
    public String taskDesc(Model model, @ModelAttribute Task task) {
        //taskService.updateTask(task);
        return "redirect:/allTasks";
    }

    @PostMapping("/completeTask")
    public String completeTask(Model model, @ModelAttribute Task task) {
        Task taskObj = (Task) model.getAttribute("task");
        //if (taskObj != null) {
            taskObj.setDone(true);
        //}
        taskService.updateTask(taskObj);
        return "redirect:/allTasks";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task) {
        taskService.updateTask(task);
        return "redirect:/allTasks";
    }

    @GetMapping("/formUpdateTask/{taskId}")
    public String formUpdateTask(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.findTaskById(id));
        return "task/updateTask";
    }

}
