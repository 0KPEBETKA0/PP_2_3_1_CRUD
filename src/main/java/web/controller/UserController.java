package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAOImpl;
import web.model.User;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UserDAOImpl userDAOImpl;

    @Autowired
    public UserController(UserDAOImpl userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("allUsers", userDAOImpl.getAllUsers());
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userDAOImpl.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAOImpl.getUser(id));
        return "edit";
    }


    @PostMapping("/{id}")
    public String saveEditedUser(@ModelAttribute("user") User user, @PathVariable(name = "id") int id){
        userDAOImpl.update(user, id);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userDAOImpl.delete(id);
        return "redirect:/users";
    }
}
