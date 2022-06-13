package com.lowes.empapp.controller;

// User Registration Page
import com.lowes.empapp.model.User;
import com.lowes.empapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class RegisterController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(Model model) {

        return new ModelAndView("register", "command", new User());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView submitRegistration(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) {
        userService.addUser(user);
        return new ModelAndView("registerSuccess");
    }

}
