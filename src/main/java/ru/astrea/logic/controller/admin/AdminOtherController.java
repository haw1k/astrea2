package ru.astrea.logic.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.astrea.logic.controller.form.Message;
import ru.astrea.logic.entity.Other;
import ru.astrea.logic.service.OtherService;

import java.util.List;
import java.util.Locale;

@RequestMapping("/admin/other")
@Controller
public class AdminOtherController {
    @Autowired
    OtherService otherService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {
        List<Other> all = otherService.findAll();
        Other other = all.get(0);
        model.addAttribute("other", other);
        return "admin/other";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editOther(Other other, RedirectAttributes redirectAttributes, Locale locale) {
        List<Other> all = otherService.findAll();
        Other otherTmp = all.get(0);
        other.setId(otherTmp.getId());
        otherService.editOther(other);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("other_edit_success", new Object[]{},locale)));
        return "redirect:/admin/other";
    }
}
