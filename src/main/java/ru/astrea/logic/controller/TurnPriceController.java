package ru.astrea.logic.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.astrea.logic.controller.form.Message;
import ru.astrea.logic.entity.TurnPrice;
import ru.astrea.logic.entity.TurnPriceGrid;
import ru.astrea.logic.service.TurnPriceService;

import javax.validation.Valid;
import java.util.Locale;

@RequestMapping("/admin/turnsprices")
@Controller
public class TurnPriceController {
    @Autowired
    TurnPriceService turnPriceService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {

        TurnPrice turnPrice = new TurnPrice();
        model.addAttribute("turnPrice", turnPrice);

        return "admin/turnsprices";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addTurnPrice(@Valid TurnPrice turnPrice, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, Locale locale) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error",messageSource.getMessage("turn_save_fail", new Object[]{},locale)));
            model.addAttribute("turnPrice", turnPrice);
            return "admin/turnsprices";
        }

        model.asMap().clear();
        turnPriceService.addTurnPrice(turnPrice);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("turn_save_success", new Object[]{}, locale)));
        return "redirect:/admin/turnsprices";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.GET)
    public String editTurnForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("turnPrice", turnPriceService.findById(id));
        return "admin/turnsprices";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.POST)
    public String editTurnPrice(@Valid TurnPrice turnPrice, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, Locale locale) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error",messageSource.getMessage("turn_save_fail", new Object[]{},locale)));
            model.addAttribute("turnPrice", turnPrice);
            return "admin/turnsprices";
        }

        model.asMap().clear();
        turnPriceService.editTurnPrice(turnPrice);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("turn_save_success", new Object[]{}, locale)));
        return "redirect:/admin/turnsprices";
    }

    @RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String deleteTrunPrice(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Locale locale) {
        turnPriceService.deleteTurnPrice(id);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("turn_delete_success", new Object[]{}, locale)));
        return "redirect:/admin/turnsprices";
    }

    @RequestMapping(value = "/turnspriceslist", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public TurnPriceGrid listTurnsPrices(@RequestParam(value = "page", required = false) Integer page,
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


        Page<TurnPrice> turnPricePage = turnPriceService.findAllByPage(pageRequest);

        TurnPriceGrid turnPriceGrid = new TurnPriceGrid();

        turnPriceGrid.setCurrentPage(turnPricePage.getNumber() + 1);
        turnPriceGrid.setTotalPages(turnPricePage.getTotalPages());
        turnPriceGrid.setTotalRecords(turnPricePage.getTotalElements());
        turnPriceGrid.setTurnPriceData(Lists.newArrayList(turnPricePage.iterator()));

        return turnPriceGrid;
    }
}
