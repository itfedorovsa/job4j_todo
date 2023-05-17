package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

/**
 * UserController
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 06.12.22
 */
@Controller
@AllArgsConstructor
@ThreadSafe
public class UserController implements UserSessionController {

    /**
     * UserService implementation
     */
    private final UserService userService;

    /**
     * Registration post page
     *
     * @param model Model
     * @param user  Empty User to fill
     * @return fail or success registration page
     */
    @PostMapping("/registration")
    public String registration(Model model,
                               @ModelAttribute User user,
                               @RequestParam("timezone.getID()") String timezone) {
        user.setTimezone(timezone);
        Optional<User> regUser = userService.add(user);
        if (regUser.isEmpty() || regUser.get().getId() == 0) {
            model.addAttribute("message", "A user with this login is already exists");
            return "redirect:/fail";
        }
        return "redirect:/success";
    }

    /**
     * Sign up form
     *
     * @param model       Model
     * @param httpSession HttpSession
     * @return addUser.html - new user creating form
     */
    @GetMapping("/formAddUser")
    public String addPost(Model model, HttpSession httpSession) {
        model.addAttribute("user", getUser(httpSession));
        model.addAttribute("timezones", getTimezones());
        return "user/addUser";
    }

    /**
     * Affirmed registration page
     *
     * @param model       Model
     * @param httpSession HttpSession
     * @return Affirmed registration page
     */
    @GetMapping("/success")
    public String success(Model model, HttpSession httpSession) {
        model.addAttribute("user", getUser(httpSession));
        return "success";
    }

    /**
     * Declined registration page
     *
     * @param model       Model
     * @param httpSession HttpSession
     * @return Declined registration page
     */
    @GetMapping("/fail")
    public String fail(Model model, HttpSession httpSession) {
        model.addAttribute("user", getUser(httpSession));
        return "error/fail";
    }

    /**
     * Start registration form
     *
     * @param model Model
     * @param fail  Fail condition
     * @return login.html - log in form
     */
    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    /**
     * Log in post page
     *
     * @param user Current user model
     * @param req  Request from DB on user presence
     * @return Data duplication warning or index page
     */
    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userDb = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
        if (userDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

    /**
     * Log out page
     *
     * @param httpSession HttpSession
     * @return log in page
     */
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/loginPage";
    }

    /**
     * User profile page
     *
     * @param model       Model
     * @param httpSession HttpSession
     * @return profile.html - current user data page
     */
    @GetMapping("/profile")
    public String profile(Model model, HttpSession httpSession) {
        model.addAttribute("user", getUser(httpSession));
        return "user/profile";
    }

    /**
     * Updating user profile
     *
     * @param model       Model
     * @param httpSession HttpSession
     * @param userId      current user id
     * @return updateProfile.html - user updating form
     */
    @GetMapping("/updateProfile/{userId}")
    public String updateProfile(Model model,
                                @PathVariable("userId") int userId,
                                HttpSession httpSession) {
        model.addAttribute("user", getUser(httpSession));
        model.addAttribute("timezones", getTimezones());
        return "user/updateProfile";
    }

    /**
     * User update post page
     *
     * @param user        current user
     * @param httpSession HttpSession
     * @return log in page to re log in
     */
    @PostMapping("/updateProfile")
    public String updatePost(Model model,
                             @ModelAttribute User user,
                             @RequestParam("timezone") String timezone,
                             HttpSession httpSession) {
        User u = (User) httpSession.getAttribute("user");
        user.setId(u.getId());
        user.setTimezone(timezone);
        userService.update(user);
        httpSession.invalidate();
        return "redirect:/loginPage";
    }

    /**
     * Create a list of all available Timezone
     *
     * @return List of all available Timezone
     */
    private List<TimeZone> getTimezones() {
        List<TimeZone> zones = new ArrayList<>();
        for (String timeId : TimeZone.getAvailableIDs()) {
            zones.add(TimeZone.getTimeZone(timeId));
        }
        return zones;
    }

}
