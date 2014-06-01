package ru.astrea.logic.controller.main.turns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.astrea.logic.entity.Turn;
import ru.astrea.logic.entity.TurnCategory;
import ru.astrea.logic.service.TurnCategoryService;
import ru.astrea.logic.service.TurnService;

import java.util.List;

@Controller
@RequestMapping({"/turns"})
public class MainTurnsController {
    @Autowired
    private TurnCategoryService turnCategoryService;
    @Autowired
    private TurnService turnService;

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {
        List<TurnCategory> categories = turnCategoryService.findAll();
        model.addAttribute("categories", categories);
        return "turns";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String main(@PathVariable Long id, Model model) {
        Turn turn = turnService.findById(id);
        model.addAttribute("turn", turn);
        return "turn";
    }
}
