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
import ru.astrea.logic.entity.Review;
import ru.astrea.logic.service.ReviewService;

import java.util.List;
import java.util.Locale;

@RequestMapping("/review")
@Controller
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    MessageSource messageSource;

    private int MAX_RECORDS = 5;

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {
        Review review = new Review();
        model.addAttribute("review", review);
        return "review";
    }

    @RequestMapping(value = "/totalPages",  method = RequestMethod.GET, consumes="application/json")
     public @ResponseBody
     int countTotalPages() {
        Page<Review> page = reviewService.findAllByPage(new PageRequest(1, MAX_RECORDS));
        int totalPages = page.getTotalPages();
        return totalPages;
    }

    @RequestMapping(value = "/reviewPage",  method = RequestMethod.POST, consumes="application/json")
    public @ResponseBody
    List<Review> reviewPage(@RequestBody PageData pageData ) {
        Page<Review> page = reviewService.findAllByPage(new PageRequest(pageData.getNumberPage() - 1, MAX_RECORDS, Sort.Direction.DESC, "id"));
        List<Review> reviews = Lists.newArrayList(page.iterator());
        return reviews;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addReview(Review review, RedirectAttributes redirectAttributes, Locale locale) {
        LocalDate creationDate = new LocalDate();
        review.setCreationDate(creationDate);

        reviewService.addReview(review);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("review_save_success", new Object[]{}, locale)));
        return "redirect:/review";
    }
}
