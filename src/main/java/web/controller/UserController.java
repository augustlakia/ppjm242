package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.Role;
import web.model.User;
import web.services.UserServImp;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserServImp userService;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        if (!userService.isCreated()) {
            List<Role> rols = new ArrayList<>();
            rols.add(new Role("ROLE_ADMIN"));
            userService.add(new User("ADMIN", "admin@admin.com", "ADMIN", rols));
            List<Role> rols2 = new ArrayList<>();
            rols2.add(new Role("ROLE_USER"));
            userService.add(new User("USER", "user@user.com", "USER", rols2));
        }
        return "login";
    }

}