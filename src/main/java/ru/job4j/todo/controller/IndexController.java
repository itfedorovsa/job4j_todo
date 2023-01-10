package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.TaskService;

import javax.servlet.http.HttpSession;

/**
 * Index Controller
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 27.11.22
 */
@Controller
@AllArgsConstructor
@ThreadSafe
public class IndexController {

    private final TaskService taskService;

    /**
     * Index page
     *
     * @param model       Model
     * @param httpSession Http Session
     * @return index.html - start page
     */
    @GetMapping("/index")
    public String index(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Guest");
        }
        model.addAttribute("user", user);
        return "index";
    }
}
