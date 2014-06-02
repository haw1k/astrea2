package ru.astrea.logic.controller.admin;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.astrea.logic.controller.form.Message;
import ru.astrea.logic.controller.form.UsernameValidator;
import ru.astrea.logic.entity.Role;
import ru.astrea.logic.entity.User;
import ru.astrea.logic.entity.UserGrid;
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

    @Autowired
    UsernameValidator usernameValidator;

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

        usernameValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors()) {
            List<Role> userRoles = roleService.findAll();
            model.addAttribute("message", new Message("error",messageSource.getMessage("user_save_fail", new Object[]{},locale)));
            model.addAttribute("userRoles", userRoles);
            model.addAttribute("user", user);
            return "admin/users";
        }
        model.asMap().clear();
        userService.addUser(user);
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
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("user_save_success", new Object[]{}, locale)));
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.GET)
    public String editTurnForm(@PathVariable("id") Long id, Model model) {

        User user = userService.findById(id);
        List<Role> userRoles = roleService.findAll();
        model.addAttribute("params","edit");
        model.addAttribute("user", user);
        model.addAttribute("userRoles", userRoles);
        return "admin/users";
    }

    @RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Locale locale) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("user_delete_success", new Object[]{}, locale)));
        return "redirect:/admin/users";
    }

    @RequestMapping("/available")
    @ResponseBody
    public String available(@RequestParam String username) {
        Boolean available = userService.findByUsername(username) == null;
        return available.toString();
    }

    @RequestMapping(value = "/userslist", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public UserGrid listTurnsPrices(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "rows", required = false) Integer rows,
                                         @RequestParam(value = "sidx", required = false) String sortBy,
                                         @RequestParam(value = "sord", required = false) String order) {
        Sort sort = null;
        String orderBy = sortBy;

        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else
                sort = new Sort(Sort.Direction.ASC, orderBy);
        }


        PageRequest pageRequest = null;

        if (sort != null) {
            pageRequest = new PageRequest(page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest(page - 1, rows);
        }


        Page<User> userPage = userService.findAllByPage(pageRequest);

        UserGrid userGrid = new UserGrid();

        userGrid.setCurrentPage(userPage.getNumber() + 1);
        userGrid.setTotalPages(userPage.getTotalPages());
        userGrid.setTotalRecords(userPage.getTotalElements());
        userGrid.setUserData(Lists.newArrayList(userPage.iterator()));

        return userGrid;
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
