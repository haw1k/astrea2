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
import ru.astrea.logic.entity.Question;
import ru.astrea.logic.entity.QuestionAnswer;
import ru.astrea.logic.entity.QuestionAnswerGrid;
import ru.astrea.logic.entity.QuestionGrid;
import ru.astrea.logic.service.QuestionAnswerService;
import ru.astrea.logic.service.QuestionService;

import java.util.Locale;

@RequestMapping("/admin/questionAnswer")
@Controller
public class AdminQuestionAnswerController {
    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionAnswerService questionAnswerService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String main() {
        return "admin/questionAnswer";
    }

    @RequestMapping(value = "/question/{id}", params = "answer", method = RequestMethod.GET)
    public String answerQuestionForm(@PathVariable("id") Long id, Model model) {
        Question question = questionService.findById(id);
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setCreationDate(question.getCreationDate());
        questionAnswer.setName(question.getName());
        questionAnswer.setEmail(question.getEmail());
        questionAnswer.setPhone(question.getPhone());
        questionAnswer.setQuestion(question.getQuestion());
        questionAnswer.setAnswer("");

        System.out.println(questionAnswer.getAnswer());
        model.addAttribute("questionAnswer", questionAnswer);
        return "admin/questionAnswer/question";
    }

    @RequestMapping(value = "/question/{id}", params = "answer", method = RequestMethod.POST)
    public String answerQuestion(@PathVariable("id") Long id, QuestionAnswer questionAnswer, RedirectAttributes redirectAttributes, Locale locale) {
        questionAnswer.setAnswer(questionAnswer.getAnswer().substring(1));
        Question question = questionService.findById(id);
        questionAnswer.setCreationDate(question.getCreationDate());
        questionAnswer.setName(question.getName());
        questionAnswer.setEmail(question.getEmail());
        questionAnswer.setPhone(question.getPhone());

        questionAnswerService.addQuestionAnswer(questionAnswer);
        questionService.deleteQuestion(id);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("question_answer_save_success", new Object[]{},locale)));

        return "redirect:/admin/questionAnswer";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.GET)
    public String answerQuestionFormEdit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("questionAnswer", questionAnswerService.findById(id));
        return "admin/questionAnswer/question";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.POST)
    public String answerQuestionEdit(@PathVariable("id") Long id, QuestionAnswer questionAnswer, Model model, RedirectAttributes redirectAttributes, Locale locale) {
        QuestionAnswer questionAnswerTmp = questionAnswerService.findById(id);
        if(questionAnswer.getAnswer() == "" || questionAnswer.getAnswer() == null) {
            Question question = new Question();
            question.setCreationDate(questionAnswerTmp.getCreationDate());
            question.setPhone(questionAnswerTmp.getPhone());
            question.setEmail(questionAnswerTmp.getEmail());
            question.setName(questionAnswerTmp.getName());
            question.setQuestion(questionAnswerTmp.getQuestion());
            questionService.addQuestion(question);
            questionAnswerService.deleteQuestionAnswer(id);
        }
        else {
            questionAnswer.setCreationDate(questionAnswerTmp.getCreationDate());
            questionAnswer.setName(questionAnswerTmp.getName());
            questionAnswer.setEmail(questionAnswerTmp.getEmail());
            questionAnswer.setPhone(questionAnswerTmp.getPhone());
            questionAnswer.setId(questionAnswerTmp.getId());
            questionAnswerService.editQuestionAnswer(questionAnswer);
        }

        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("question_answer_save_success", new Object[]{},locale)));

        return "redirect:/admin/questionAnswer";
    }

    @RequestMapping(value = "/question/{id}", params = "delete", method = RequestMethod.GET)
    public String deleteQuestion(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Locale locale) {
        questionService.deleteQuestion(id);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("question_delete_success", new Object[]{}, locale)));
        return "redirect:/admin/questionAnswer";
    }

    @RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String deleteQuestionAnswer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Locale locale) {
        questionAnswerService.deleteQuestionAnswer(id);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("question_delete_success", new Object[]{}, locale)));
        return "redirect:/admin/questionAnswer";
    }

    @RequestMapping(value = "/questionslist", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public QuestionGrid listQuestion(@RequestParam(value = "page", required = false) Integer page,
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

        System.out.println(page + rows);

        if (sort != null) {
            pageRequest = new PageRequest(page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest(page - 1, rows);
        }



        Page<Question> questionPage = questionService.findAllByPage(pageRequest);

        QuestionGrid questionGrid = new QuestionGrid();

        questionGrid.setCurrentPage(questionPage.getNumber() + 1);
        questionGrid.setTotalPages(questionPage.getTotalPages());
        questionGrid.setTotalRecords(questionPage.getTotalElements());
        questionGrid.setQuestionData(Lists.newArrayList(questionPage.iterator()));

        return questionGrid;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public QuestionAnswerGrid listQuestionAnswer(@RequestParam(value = "page", required = false) Integer page,
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



        Page<QuestionAnswer> questionAnswerPage = questionAnswerService.findAllByPage(pageRequest);

        QuestionAnswerGrid questionAnswerGrid = new QuestionAnswerGrid();

        questionAnswerGrid.setCurrentPage(questionAnswerPage.getNumber() + 1);
        questionAnswerGrid.setTotalPages(questionAnswerPage.getTotalPages());
        questionAnswerGrid.setTotalRecords(questionAnswerPage.getTotalElements());
        questionAnswerGrid.setQuestionAnswerData(Lists.newArrayList(questionAnswerPage.iterator()));

        return questionAnswerGrid;
    }
}
