package ru.astrea.logic.controller.admin;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.astrea.logic.controller.form.Message;
import ru.astrea.logic.entity.Consultation;
import ru.astrea.logic.entity.ConsultationGrid;
import ru.astrea.logic.service.ConsultationService;

import java.util.Locale;

@RequestMapping("/admin/consultation")
@Controller
public class AdminConsultationController {
    @Autowired
    ConsultationService consultationService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String main() {
        return "admin/consultation";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.GET)
    public String editConsultationForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("consultation", consultationService.findById(id));
        return "admin/consultation/edit";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.POST)
    public String editConsultation(@PathVariable("id") Long id, Consultation consultation, RedirectAttributes redirectAttributes, Locale locale) {
        Consultation tmpConsultation = consultationService.findById(id);
        tmpConsultation.setStatus(consultation.getStatus());
        consultationService.editConsultation(tmpConsultation);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("consultation_edit_success", new Object[]{},locale)));
        return "redirect:/admin/consultation";
    }

    @RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String deleteConsultation(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Locale locale) {
        consultationService.deleteConsultation(id);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("consultation_delete_success", new Object[]{}, locale)));
        return "redirect:/admin/consultation";
    }

    @RequestMapping(value = "/consultationlist", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public ConsultationGrid listConsultation(@RequestParam(value = "page", required = false) Integer page,
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



        Page<Consultation> consultationPage = consultationService.findAllByPage(pageRequest);

        ConsultationGrid consultationGrid = new ConsultationGrid();

        consultationGrid.setCurrentPage(consultationPage.getNumber() + 1);
        consultationGrid.setTotalPages(consultationPage.getTotalPages());
        consultationGrid.setTotalRecords(consultationPage.getTotalElements());
        consultationGrid.setConsultationData(Lists.newArrayList(consultationPage.iterator()));

        return consultationGrid;
    }
}
