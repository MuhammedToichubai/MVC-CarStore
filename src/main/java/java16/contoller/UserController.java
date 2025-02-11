package java16.contoller;

import java16.models.User;
import java16.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
       List<User> users =  userService.findAll();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/update-form/{id}")
    public String updateForm(@PathVariable("id") Long userId,  Model model) {
       User foundUser = userService.findById(userId);
       model.addAttribute("user", foundUser);
        return "user/update-form";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.save(user, false);
        return "redirect:/users";
    }


}
