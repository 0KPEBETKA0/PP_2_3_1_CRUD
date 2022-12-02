package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAOImpl;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("allUsers", userServiceImpl.getAllUsers());
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userServiceImpl.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.getUser(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String saveEditedUser(@ModelAttribute("user") User user) {
        userServiceImpl.update(user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userServiceImpl.delete(id);
        return "redirect:/users";
    }
}
