package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.services.UserServImp;

import java.applet.AppletContext;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FinishedUserController {

    @Autowired
    private UserServImp userService;

    @GetMapping(value = "/")
    public String printWelcome(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "/index";
    }

    @GetMapping("/user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        System.out.println(userService.findById(id));
        model.addAttribute("user", userService.findById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/user")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.Update(user);
        return "redirect:/";
    }
    @DeleteMapping("user/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.Delete(id);
        return "redirect:/";
    }

}