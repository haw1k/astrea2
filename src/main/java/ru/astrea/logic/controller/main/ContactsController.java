package ru.astrea.logic.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.astrea.logic.entity.Other;
import ru.astrea.logic.service.OtherService;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactsController {
    @Autowired
    OtherService otherService;

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {
        List<Other> all = otherService.findAll();
        Other other = all.get(0);
        model.addAttribute("other", other);
        return "contacts";
    }
}
