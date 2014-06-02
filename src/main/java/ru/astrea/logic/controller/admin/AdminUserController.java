package ru.astrea.logic.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.astrea.logic.controller.form.Message;
import ru.astrea.logic.entity.Role;
import ru.astrea.logic.entity.User;
import ru.astrea.logic.service.RoleService;
import ru.astrea.logic.service.UserService;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Locale;

@RequestMapping("/admin/users")
@Controller
public class AdminUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> userRoles = roleService.findAll();
        model.addAttribute("userRoles", userRoles);
        return "admin/users";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, Locale locale) {

        System.out.println(bindingResult);
        if(bindingResult.hasErrors()) {
            List<Role> userRoles = roleService.findAll();
            model.addAttribute("message", new Message("error",messageSource.getMessage("user_save_fail", new Object[]{},locale)));
            model.addAttribute("userRoles", userRoles);
            model.addAttribute("user", user);
            return "admin/users";
        }
        model.asMap().clear();
        System.out.println(bindingResult);
        userService.addUser(user);
        System.out.println(bindingResult);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("user_save_success", new Object[]{}, locale)));
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.POST)
    public String editTurn(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, Locale locale) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error",messageSource.getMessage("user_save_fail", new Object[]{},locale)));
            List<Role> userRoles = roleService.findAll();
            model.addAttribute("userRoles", userRoles);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            model.addAttribute("user", user);
            return "admin/users";
        }

        model.asMap().clear();
        userService.addUser(user);
        System.out.println(user.getPassword());
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("user_save_success", new Object[]{}, locale)));
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.GET)
    public String editTurnForm(@PathVariable("id") Long id, Model model) {

        User user = userService.findById(id);
        List<Role> userRoles = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("userRoles", userRoles);
        return "admin/users";
    }

    class RoleEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text){
            Long id = Long.parseLong(text);
            Role role = roleService.findById(id);
            setValue(role);
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, new RoleEditor());
    }
}
