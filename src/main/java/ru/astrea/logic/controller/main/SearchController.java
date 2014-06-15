package ru.astrea.logic.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.astrea.logic.entity.*;
import ru.astrea.logic.service.*;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private OtherService otherService;

    @Autowired
    private TurnCategoryService turnCategoryService;

    @Autowired
    private TurnService turnService;

    @Autowired
    private TurnPriceService turnPriceService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private QuestionAnswerService questionAnswerService;

    @RequestMapping(method = RequestMethod.POST)
    public String main(String search, Model model) {
        System.out.println(search);
        int size = 0;
        if(search != null && search != "") {
            List<Other> allOther = otherService.findByCriterion(search);
            if (allOther != null) {
                size+=allOther.size();
                model.addAttribute("aboutSize", allOther.size());
            }
            else {
                model.addAttribute("aboutSize", 0);
            }

            List<TurnCategory> categories = turnCategoryService.findByCriterion(search);
            if(categories != null) {
                int categoriesSize = categories.size();
                size+=categoriesSize;
                model.addAttribute("categoriesSize", categoriesSize);
            }
            else {
                model.addAttribute("categoriesSize", 0);
            }

            List<Turn> turns = turnService.findByCriterion(search);
            if(turns != null) {
                int turnsSize = turns.size();
                size+=turnsSize;
                model.addAttribute("turnsSize", turnsSize);
            }
            else {
                model.addAttribute("turnsSize", 0);
            }

            List<TurnPrice> turnPrices = turnPriceService.findByCriterion(search);
            if(turnPrices != null) {
                int turnPricesSize = turnPrices.size();
                size+=turnPricesSize;
                model.addAttribute("turnPricesSize", turnPricesSize);
            }
            else {
                model.addAttribute("turnPricesSize", 0);
            }

            List<QuestionAnswer> questionAnswers = questionAnswerService.findByCriterion(search);
            if(questionAnswers != null) {
                int questionAnswersSize = questionAnswers.size();
                size+=questionAnswersSize;
                model.addAttribute("questionAnswersSize", questionAnswersSize);
            }
            else {
                model.addAttribute("questionAnswersSize", 0);
            }

            List<Review> reviews = reviewService.findByCriterion(search);
            if(reviews != null) {
                int reviewsSize = reviews.size();
                size+=reviewsSize;
                model.addAttribute("reviewsSize", reviewsSize);
            }
            else {
                model.addAttribute("reviewsSize", 0);
            }
        }
        model.addAttribute("size", size);
        model.addAttribute("search", search);
        return "search";
    }

    @RequestMapping(params = "turns", method = RequestMethod.GET)
    public String searchTurns(@RequestParam String turns, Model model) {
        List<Turn> foundTurns = turnService.findByCriterion(turns);
        model.addAttribute("turns", foundTurns);
        return "searchTurns";
    }

    @RequestMapping(params = "categories", method = RequestMethod.GET)
    public String searchCategories(@RequestParam String categories, Model model) {
        List<TurnCategory> foundCategories = turnCategoryService.findByCriterion(categories);
        System.out.println(foundCategories);
        model.addAttribute("foundCategories", foundCategories);
        return "searchCategories";
    }

    @RequestMapping(params = "prices", method = RequestMethod.GET)
    public String searchPrices(@RequestParam String prices, Model model) {
        List<TurnPrice> foundPrices = turnPriceService.findByCriterion(prices);
        model.addAttribute("prices", foundPrices);
        return "searchPrices";
    }

    @RequestMapping(params = "questions", method = RequestMethod.GET)
    public String searchQuestions(@RequestParam String questions, Model model) {
        List<QuestionAnswer> foundQuestions = questionAnswerService.findByCriterion(questions);
        model.addAttribute("questions", foundQuestions);
        return "searchQuestions";
    }

    @RequestMapping(params = "reviews", method = RequestMethod.GET)
    public String searchReviews(@RequestParam String reviews, Model model) {
        List<Review> foundReviews = reviewService.findByCriterion(reviews);
        model.addAttribute("foundReviews", foundReviews);
        return "searchReviews";
    }
}
