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

import java.util.Optional;

/**
 * Task Controller
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 27.11.22
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
     *
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
     *
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
     *
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
     *
     * @return newTask.html - new task creating page
     */
    @GetMapping("/newTask")
    public String newTask() {
        return "task/newTask";
    }

    /**
     * Post method for adding a task
     *
     * @param task "task" attribute in model
     * @return all tasks page
     */
    @PostMapping("/formAddTask")
    public String formAddTask(@ModelAttribute Task task) {
        task.setDone(false);
        taskService.addTask(task);
        return "redirect:/allTasks";
    }

    /**
     * Page of task's description
     *
     * @param model Model
     * @param id    Current task id
     * @return taskDesc.html - page with task's description
     */
    @GetMapping("/formTaskDesc/{taskId}")
    public String formTaskDesc(Model model, @PathVariable("taskId") int id) {
        Optional<Task> taskById = taskService.findTaskById(id);
        Task taskObj = new Task();
        if (taskById.isPresent()) {
            taskObj = taskById.get();
            System.out.println("formTaskDesc ID: " + taskObj.getId());
        }
        model.addAttribute("task", taskObj);
        return "task/taskDesc";
    }

    /**
     * Post method for marking task as done
     *
     * @param task "task" attribute in model
     * @return all tasks page
     */
    @PostMapping("/completeTask")
    public String completeTask(@ModelAttribute Task task) {
        task.setDone(true);
        taskService.updateTask(task);
        return "redirect:/allTasks";
    }

    /**
     * Post method for deleting task
     *
     * @param task "task" attribute in model
     * @return all tasks page
     */
    @PostMapping("/deleteTask")
    public String deleteTask(@ModelAttribute Task task) {
        System.out.println(task.getId());
        taskService.deleteTask(task);
        return "redirect:/allTasks";
    }

    /**
     * Post method for updating task
     *
     * @param task "task" attribute in model
     * @return all tasks page
     */
    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task) {
        taskService.updateTask(task);
        return "redirect:/allTasks";
    }

    /**
     * Page of task's description
     *
     * @param model Model
     * @param id    Current task id
     * @return updateTask.html - task updating page
     */
    @GetMapping("/formUpdateTask/{taskId}")
    public String formUpdateTask(Model model, @PathVariable("taskId") int id) {
        Optional<Task> taskById = taskService.findTaskById(id);
        Task taskObj = new Task();
        if (taskById.isPresent()) {
            taskObj = taskById.get();
            System.out.println("formTaskDesc ID: " + taskObj.getId());
        }
        model.addAttribute("task", taskObj);
        return "task/updateTask";
    }

}
