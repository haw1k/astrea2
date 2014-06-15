package ru.astrea.logic.controller.main.questionAnswer;

import com.google.common.collect.Lists;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.astrea.logic.controller.form.Message;
import ru.astrea.logic.entity.Question;
import ru.astrea.logic.entity.QuestionAnswer;
import ru.astrea.logic.service.QuestionAnswerService;
import ru.astrea.logic.service.QuestionService;

import java.util.List;
import java.util.Locale;

@RequestMapping("/questionAnswer")
@Controller
public class QuestionAnswerController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionAnswerService questionAnswerService;

    @Autowired
    MessageSource messageSource;

    private int MAX_RECORDS = 5;

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {
        Question question = new Question();
        model.addAttribute("question", question);
        return "questionAnswer";
    }

    @RequestMapping(value = "/totalPages",  method = RequestMethod.GET, consumes="application/json")
     public @ResponseBody
     int countTotalPages() {
        Page<QuestionAnswer> page = questionAnswerService.findAllByPage(new PageRequest(1, MAX_RECORDS));
        int totalPages = page.getTotalPages();
        return totalPages;
    }

    @RequestMapping(value = "/questionsAnswersPage",  method = RequestMethod.POST, consumes="application/json")
    public @ResponseBody
    List<QuestionAnswer> questionAnswersPage(@RequestBody PageData pageData ) {
        Page<QuestionAnswer> page = questionAnswerService.findAllByPage(new PageRequest(pageData.getNumberPage() - 1, MAX_RECORDS, Sort.Direction.DESC, "id"));
        List<QuestionAnswer> questionAnswers = Lists.newArrayList(page.iterator());
        return questionAnswers;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addQuestion(Question question, RedirectAttributes redirectAttributes, Locale locale) {
        LocalDate creationDate = new LocalDate();
        question.setCreationDate(creationDate);

        questionService.addQuestion(question);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("question_save_success", new Object[]{}, locale)));
        return "redirect:/questionAnswer";
    }
}
