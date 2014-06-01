package ru.astrea.logic.controller.main.turns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.astrea.logic.entity.TurnCategory;
import ru.astrea.logic.service.TurnCategoryService;

@Controller
@RequestMapping({"/turns/categories"})
public class MainCategoriesController {
    @Autowired
    private TurnCategoryService turnCategoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String main(@PathVariable Long id, Model model) {
        TurnCategory category = turnCategoryService.findById(id);
        model.addAttribute("category", category);
        return "turns/category";
    }

    @RequestMapping(value="/img/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] downloadImg(@PathVariable("id") Long id) {
        TurnCategory turnCategory = turnCategoryService.findById(id);
        return  turnCategory.getImg();
    }
}
