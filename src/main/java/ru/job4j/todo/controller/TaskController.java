package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.TaskService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

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
public class TaskController {
    private final TaskService taskService;

    /**
     * All tasks page
     *
     * @param model       Model
     * @param httpSession HTTP Session
     * @return allTasks.html - all tasks from list
     */
    @GetMapping("/allTasks")
    public String allTasks(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        model.addAttribute("allTasks", taskService.findAllTasks(user.getId()));
        model.addAttribute("user", user);
        return "task/allTasks";
    }

    /**
     * New tasks page
     *
     * @param model       Model
     * @param httpSession HTTP Session
     * @return newTasks.html - new (opened) tasks from list
     */
    @GetMapping("/newTasks")
    public String newTasks(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        model.addAttribute("newTasks", taskService.findNewTasks(user.getId()));
        model.addAttribute("user", user);
        return "task/newTasks";
    }

    /**
     * Finished tasks page
     *
     * @param model       Model
     * @param httpSession HTTP Session
     * @return finishedTasks.html - finished (closed) tasks from list
     */
    @GetMapping("/finishedTasks")
    public String finishedTasks(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        model.addAttribute("finishedTasks", taskService.findFinishedTasks(user.getId()));
        model.addAttribute("user", user);
        return "task/finishedTasks";
    }

    /**
     * New task creating page
     *
     * @param model       Model
     * @param httpSession HTTP Session
     * @return newTask.html - new task creating page
     */
    @GetMapping("/newTask")
    public String newTask(Model model, HttpSession httpSession) {
        model.addAttribute("user", getUser(httpSession));
        model.addAttribute("priorities", taskService.findAllPriorities());
        return "task/newTask";
    }


    /**
     * Post method for adding a task
     *
     * @param task        "task" attribute in model
     * @param httpSession HTTP Session
     * @return All tasks page
     */
    @PostMapping("/formAddTask")
    public String formAddTask(@ModelAttribute Task task, HttpSession httpSession) {
        task.setDone(false);
        taskService.addTask(getUser(httpSession), task);
        return "redirect:/allTasks";
    }

    /**
     * Page of task's description
     *
     * @param model       Model
     * @param id          Current task id
     * @param httpSession HTTP Session
     * @return taskDesc.html - page with task's description
     */
    @GetMapping("/formTaskDesc/{taskId}")
    public String formTaskDesc(Model model,
                               @PathVariable("taskId") int id,
                               HttpSession httpSession) {
        Optional<Task> taskById = taskService.findTaskById(id);
        Task taskObj = new Task();
        if (taskById.isPresent()) {
            taskObj = taskById.get();
        }
        model.addAttribute("task", taskObj);
        model.addAttribute("user", getUser(httpSession));
        return "task/taskDesc";
    }

    /**
     * Post method for marking task as done
     *
     * @param task        "task" attribute in model
     * @param httpSession HTTP Sessiond
     * @return All tasks page
     */
    @PostMapping("/completeTask")
    public String completeTask(@ModelAttribute Task task, HttpSession httpSession) {
        Optional<Task> taskById = taskService.findTaskById(task.getId());
        Task taskObj = new Task();
        if (taskById.isPresent()) {
            taskObj = taskById.get();
        }
        task.setPriority(taskObj.getPriority());
        System.out.println(task);
        taskService.markAsDone(getUser(httpSession), task);
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
        Optional<Task> taskById = taskService.findTaskById(id);
        Task taskObj = new Task();
        if (taskById.isPresent()) {
            taskObj = taskById.get();
        }
        model.addAttribute("task", taskObj);
        model.addAttribute("user", getUser(httpSession));
        model.addAttribute("priorities", taskService.findAllPriorities());
        return "task/updateTask";
    }

    /**
     * Post method for updating task
     *
     * @param task        "task" attribute in model
     * @param isDone      Task's "isDone" field value
     * @param httpSession HTTP Session
     * @return All tasks page
     */
    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task,
                             @ModelAttribute("isDone") String isDone,
                             HttpSession httpSession) {
        taskService.updateTask(getUser(httpSession), Boolean.parseBoolean(isDone), task);
        return "redirect:/allTasks";
    }

    /**
     * Gives "Guest" name if user is unregistered
     *
     * @param httpSession HTTPSession
     * @return User with "Guest" name or user with currrent name
     */
    private User getUser(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Guest");
        }
        return user;
    }

}
