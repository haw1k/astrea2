package ru.astrea.logic.controller.main.turnprices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.astrea.logic.entity.TurnPrice;
import ru.astrea.logic.service.TurnPriceService;

import java.util.List;

@RequestMapping("/prices")
@Controller
public class TurnPriceController {
    @Autowired
    TurnPriceService turnPriceService;

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {

        List<TurnPrice> prices = turnPriceService.findAll();
        model.addAttribute("prices", prices);

        return "turnprices";
    }
}
