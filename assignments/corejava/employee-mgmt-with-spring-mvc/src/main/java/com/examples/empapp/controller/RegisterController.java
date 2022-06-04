package com.examples.empapp.controller;

// User Registration Page


import com.examples.empapp.exception.LoginException;
import com.examples.empapp.model.Employee;
import com.examples.empapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class RegisterController {


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(Model model) {
        return new ModelAndView("register", "command", new User());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView submitRegister(Model model) {
        return new ModelAndView("registerSuccess");
    }

}
