package ru.astrea.logic.controller.main.consultation;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.astrea.logic.entity.Consultation;
import ru.astrea.logic.service.ConsultationService;

@Controller
public class ConsultationController {
    @Autowired
    ConsultationService consultationService;

    @RequestMapping(value = "consultation/add",  method = RequestMethod.POST, consumes="application/json")
    public @ResponseBody
    String addConsultation(@RequestBody Consultation consultation) {
        LocalDate dateTime = new LocalDate();
        consultation.setCreationDate(dateTime);
        consultation.setProcessed(false);
        consultationService.addConsultation(consultation);
        return "success";
    }
}
