package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.CategoryService;
import ru.job4j.todo.service.PriorityService;
import ru.job4j.todo.service.TaskService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TimeZone;

/**
 * Task Controller
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 27.11.22
 */
@Controller
@AllArgsConstructor
@ThreadSafe
public class TaskController implements UserSessionController {

    /**
     * Date and time formatter
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd");

    /**
     * TaskService implementation
     */
    private final TaskService taskService;

    /**
     * PriorityService implementation
     */
    private final PriorityService priorityService;

    /**
     * CategoryService implementation
     */
    private final CategoryService categoryService;

    /**
     * Add User in Model by "user" key in all Model in this controller
     *
     * @param httpSession HTTPSession
     * @return User
     */
    @ModelAttribute("user")
    public User addUserToModel(HttpSession httpSession) {
        return getUser(httpSession);
    }

    /**
     * All tasks page
     *
     * @param model       Model
     * @param httpSession HTTPSession
     * @return allTasks.html - all tasks from list
     */
    @GetMapping("/allTasks")
    public String allTasks(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        List<Task> allTasks = taskService.findAllTasks(user.getId());
        getFormattedTasks(user, allTasks);
        model.addAttribute("allTasks", allTasks);
        return "task/allTasks";
    }

    /**
     * New tasks page
     *
     * @param model       Model
     * @param httpSession HTTPSession
     * @return newTasks.html - new (opened) tasks from list
     */
    @GetMapping("/newTasks")
    public String newTasks(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        List<Task> newTasks = taskService.findNewTasks(user.getId());
        getFormattedTasks(user, newTasks);
        model.addAttribute("newTasks", newTasks);
        return "task/newTasks";
    }

    /**
     * Finished tasks page
     *
     * @param model       Model
     * @param httpSession HTTPSession
     * @return finishedTasks.html - finished (closed) tasks from list
     */
    @GetMapping("/finishedTasks")
    public String finishedTasks(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        List<Task> finishedTasks = taskService.findFinishedTasks(user.getId());
        getFormattedTasks(user, finishedTasks);
        model.addAttribute("finishedTasks", finishedTasks);
        return "task/finishedTasks";
    }

    /**
     * New task creating page
     *
     * @param model       Model
     * @param httpSession HTTPSession
     * @return newTask.html - new task creating page
     */
    @GetMapping("/newTask")
    public String newTask(Model model, HttpSession httpSession) {
        model.addAttribute("priorities", priorityService.findAllPriorities());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "task/newTask";
    }


    /**
     * Post method for adding a task
     *
     * @param task        "task" model attribute
     * @param httpSession HTTPSession
     * @return All tasks page
     */
    @PostMapping("/createTask")
    public String createTask(@ModelAttribute Task task,
                             @RequestParam("priority.id") int priorityId,
                             @RequestParam List<Integer> categoriesIds,
                             HttpSession httpSession) {
        Priority priorityById = priorityService.getPriorityById(priorityId)
                .orElseThrow(() -> new NoSuchElementException("Priority with id " + priorityId + " is missing."));
        task.setPriority(priorityById);
        List<Category> categories = categoryService.findCategoriesByIds(categoriesIds);
        task.setCategories(categories);
        taskService.addTask(getUser(httpSession), task);
        return "redirect:/allTasks";
    }

    /**
     * Page of task's description
     *
     * @param model       Model
     * @param id          Current task id
     * @param httpSession HTTPSession
     * @return taskDesc.html - page with task's description
     */
    @GetMapping("/formTaskDesc/{taskId}")
    public String formTaskDesc(Model model,
                               @PathVariable("taskId") int id,
                               HttpSession httpSession) {
        Task taskById = taskService.findTaskById(id)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + id + " is missing."));
        model.addAttribute("task", taskById);
        return "task/taskDesc";
    }

    /**
     * Post method for marking task as done
     *
     * @param task        "task" attribute in model
     * @param httpSession HTTPSession
     * @return All tasks page
     */
    @PostMapping("/completeTask")
    public String completeTask(@ModelAttribute Task task, HttpSession httpSession) {
        int taskId = task.getId();
        Task taskById = taskService.findTaskById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + taskId + " is missing."));
        Priority priority = taskById.getPriority();
        List<Category> categories = taskById.getCategories();
        taskService.markAsDone(getUser(httpSession), task, priority, categories);
        return "redirect:/allTasks";
    }

    /**
     * Post method for deleting task
     *
     * @param task "task" attribute in model
     * @return All tasks page
     */
    @PostMapping("/deleteTask")
    public String deleteTask(@ModelAttribute Task task) {
        taskService.deleteTask(task);
        return "redirect:/allTasks";
    }

    /**
     * Page of task's description
     *
     * @param model       Model
     * @param id          Current task id
     * @param httpSession HTTP Session
     * @return updateTask.html - task updating page
     */
    @GetMapping("/formUpdateTask/{taskId}")
    public String formUpdateTask(Model model,
                                 @PathVariable("taskId") int id,
                                 HttpSession httpSession) {
        Task taskById = taskService.findTaskById(id)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + id + " is missing."));
        model.addAttribute("task", taskById);
        model.addAttribute("priorities", priorityService.findAllPriorities());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "task/updateTask";
    }

    /**
     * Post method for updating task
     *
     * @param task        "task" attribute in model
     * @param isDone      Task's "isDone" field value
     * @param httpSession HTTPSession
     * @return All tasks page
     */
    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task,
                             @ModelAttribute("isDone") String isDone,
                             @RequestParam("priority.id") int priorityId,
                             @RequestParam List<Integer> categoriesIds,
                             HttpSession httpSession) {
        Priority priorityById = priorityService.getPriorityById(priorityId)
                .orElseThrow(() -> new NoSuchElementException("Priority with id " + priorityId + " is missing."));
        task.setPriority(priorityById);
        List<Category> categories = categoryService.findCategoriesByIds(categoriesIds);
        task.setCategories(categories);
        taskService.updateTask(getUser(httpSession), Boolean.parseBoolean(isDone), task);
        return "redirect:/allTasks";
    }

    /**
     * Changes tasks' LocalDateTime to formatted LDT with user's timezone
     *
     * @param user  Current User
     * @param tasks List of all, new or finished tasks
     * @return List of tasks with changed LocalDateTime
     */
    private List<Task> getFormattedTasks(User user, List<Task> tasks) {
        String timezone = user.getTimezone();
        String defaultTimezone = TimeZone.getDefault().getID();

        if (timezone == null) {
            timezone = defaultTimezone;
        }
        for (Task task : tasks) {
            String formatted = task.getCreated()
                    .atZone(ZoneId.of(defaultTimezone))
                    .withZoneSameInstant(ZoneId.of(timezone))
                    .format(FORMATTER);
            task.setCreated(LocalDateTime.parse(formatted, FORMATTER));
        }
        return tasks;
    }

}
