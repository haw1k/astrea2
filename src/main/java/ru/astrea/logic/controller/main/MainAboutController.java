package ru.astrea.logic.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.astrea.logic.service.TurnService;

@Controller
@RequestMapping({"/", "/about"})
public class MainAboutController {
    @Autowired
    private TurnService turnService;

    @RequestMapping(method = RequestMethod.GET)
    public String main() {
        return "about";
    }

}
