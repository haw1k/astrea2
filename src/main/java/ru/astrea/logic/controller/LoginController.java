package ru.astrea.logic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class LoginController {

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, Locale locale,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", messageSource.getMessage("login_error", new Object[]{},locale));
        }

        if (logout != null) {
            model.addAttribute("msg", messageSource.getMessage("logout_success", new Object[]{},locale));
        }
        return "login";
    }
}
