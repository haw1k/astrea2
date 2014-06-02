package ru.astrea.logic.controller.admin;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.joda.time.LocalDate;
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
import ru.astrea.logic.controller.form.ImgValidator;
import ru.astrea.logic.controller.form.Message;
import ru.astrea.logic.entity.Turn;
import ru.astrea.logic.entity.TurnCategory;
import ru.astrea.logic.entity.TurnCategoryGrid;
import ru.astrea.logic.entity.TurnGrid;
import ru.astrea.logic.service.TurnCategoryService;
import ru.astrea.logic.service.TurnService;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

@RequestMapping({"admin", "admin/turns"})
@Controller
public class AdminTurnController {
    @Autowired
    private TurnService turnService;

    @Autowired
    private TurnCategoryService turnCategoryService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    ImgValidator imgValidator;

    @RequestMapping(method = RequestMethod.GET)
     public String main() {
        return "admin/turns";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.POST)
    public String editTurn(@Valid Turn turn, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, Locale locale) {
        TurnCategory turnCategory = turnCategoryService.findById(turn.getTurnCategory().getId());
        turn.setTurnCategory(turnCategory);

        if(bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error",messageSource.getMessage("turn_save_fail", new Object[]{},locale)));
            List<TurnCategory> categories = turnCategoryService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("turn", turn);
            return "admin/turns/edit";
        }
        model.asMap().clear();
        turnService.editTurn(turn);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("turn_save_success", new Object[]{},locale)));

        return "redirect:/admin/turns";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.GET)
    public String editTurnForm(@PathVariable("id") Long id, Model model) {

        List<TurnCategory> categories = turnCategoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("turn", turnService.findById(id));
        return "admin/turns/edit";
    }

    @RequestMapping(params = "add", method = RequestMethod.POST)
    public String addTurn(@Valid Turn turn, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, Locale locale) {
        TurnCategory turnCategory = turnCategoryService.findById(turn.getTurnCategory().getId());
        turn.setTurnCategory(turnCategory);

        if(bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error",messageSource.getMessage("turn_save_fail", new Object[]{},locale)));
            List<TurnCategory> categories = turnCategoryService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("turn", turn);
            return "admin/turns/edit";
        }
        model.asMap().clear();
        turnService.addTurn(turn);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("turn_save_success", new Object[]{}, locale)));
        return "redirect:/admin/turns/";
    }

    @RequestMapping(params = "add", method = RequestMethod.GET)
    public String addTurnForm(Model model) {
        List<TurnCategory> categories = turnCategoryService.findAll();
        model.addAttribute("categories", categories);
        Turn turn = new Turn();

        LocalDate dateTime = new LocalDate();
        turn.setCreationDate(dateTime);

        model.addAttribute("turn", turn);
        return "admin/turns/edit";
    }

    @RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String deleteTrun(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Locale locale) {
            turnService.deleteTurn(id);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("turn_delete_success", new Object[]{}, locale)));
        return "redirect:/admin/turns";
    }

    @RequestMapping(value = "/category/{id}", params = "edit", method = RequestMethod.POST)
    public String editCategory(@PathVariable("id") Long id, TurnCategory turnCategory, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, Locale locale,@RequestParam(value="file", required=false) Part file) {

        if (file != null) {
            byte[] fileContent = null;
            try {
                InputStream inputStream = file.getInputStream();
                if (inputStream == null) {}
                fileContent = IOUtils.toByteArray(inputStream);
                turnCategory.setImg(fileContent);
            } catch (IOException ex) {
            }
            turnCategory.setImg(fileContent);

            if(turnCategory.getImg().length == 0) {
                turnCategory.setImg(turnCategoryService.findById(id).getImg());
            }
        }

        imgValidator.validate(turnCategory, bindingResult);

        if(bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error",messageSource.getMessage("category_save_fail", new Object[]{},locale)));
            model.addAttribute("turnCategory", turnCategory);

            return "admin/turns/category";
        }

        model.asMap().clear();
        turnCategoryService.editCategory(turnCategory);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("category_save_success", new Object[]{},locale)));
        return "redirect:/admin/turns/category/" + id +"?edit";
    }

    @RequestMapping(value = "/category/{id}", params = "edit", method = RequestMethod.GET)
    public String editCategoryForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("turnCategory", turnCategoryService.findById(id));
        return "admin/turns/category";
    }

    @RequestMapping(value = "/turnslist", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public TurnGrid listTurns(@RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "rows", required = false) Integer rows,
                                @RequestParam(value = "sidx", required = false) String sortBy,
                                @RequestParam(value = "sord", required = false) String order) {
        Sort sort = null;
        String orderBy = sortBy;

        if (orderBy != null && orderBy.equals("creationDateString")) orderBy = "creationDate";

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


        Page<Turn> turnPage = turnService.findAllByPage(pageRequest);

        TurnGrid turnGrid = new TurnGrid();

        turnGrid.setCurrentPage(turnPage.getNumber() + 1);
        turnGrid.setTotalPages(turnPage.getTotalPages());
        turnGrid.setTotalRecords(turnPage.getTotalElements());
        turnGrid.setTurnData(Lists.newArrayList(turnPage.iterator()));

        return turnGrid;
    }

    @RequestMapping(value = "/categorieslist", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public TurnCategoryGrid listCategories(@RequestParam(value = "page", required = false) Integer page,
                         @RequestParam(value = "rows", required = false) Integer rows,
                         @RequestParam(value = "sidx", required = false) String sortBy,
                         @RequestParam(value = "sord", required = false) String order) {
        Sort sort = null;
        String orderBy = sortBy;

        if (orderBy != null && orderBy.equals("creationDateString")) orderBy = "creationDate";

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

        Page<TurnCategory> categoryPage = turnCategoryService.findAllByPage(pageRequest);

        TurnCategoryGrid categoryGrid = new TurnCategoryGrid();

        categoryGrid.setCurrentPage(categoryPage.getNumber() + 1);
        categoryGrid.setTotalPages(categoryPage.getTotalPages());
        categoryGrid.setTotalRecords(categoryPage.getTotalElements());
        categoryGrid.setTurnCategoryData(Lists.newArrayList(categoryPage.iterator()));
        return categoryGrid;
    }

}
