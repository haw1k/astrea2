package ru.astrea.logic.controller.admin;

import com.google.common.collect.Lists;
import org.joda.time.LocalDate;
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
import ru.astrea.logic.entity.Review;
import ru.astrea.logic.entity.ReviewGrid;
import ru.astrea.logic.service.ReviewService;

import java.util.Locale;

@RequestMapping("/admin/reviews")
@Controller
public class AdminReviewController {
    @Autowired
    ReviewService reviewService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {

        Review review = new Review();
        model.addAttribute("review", review);
        return "admin/reviews";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addReview(Review review, RedirectAttributes redirectAttributes, Locale locale) {

        LocalDate creationDate = new LocalDate();
        review.setCreationDate(creationDate);

        reviewService.addReview(review);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("review_save_success", new Object[]{}, locale)));
        return "redirect:/admin/reviews";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.GET)
    public String editReviewForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("review", reviewService.findById(id));
        return "admin/reviews";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.POST)
    public String editReview(@PathVariable("id") Long id, Review review, RedirectAttributes redirectAttributes, Locale locale) {
        review.setCreationDate(reviewService.findById(id).getCreationDate());

        reviewService.editReview(review);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("review_save_success", new Object[]{}, locale)));
        return "redirect:/admin/reviews";
    }

    @RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String deleteReview(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Locale locale) {
        reviewService.deleteReview(id);
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("review_delete_success", new Object[]{}, locale)));
        return "redirect:/admin/reviews";
    }

    @RequestMapping(value = "/reviewslist", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public ReviewGrid listReviews(@RequestParam(value = "page", required = false) Integer page,
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


        Page<Review> reviewPage = reviewService.findAllByPage(pageRequest);

        ReviewGrid reviewGrid = new ReviewGrid();

        reviewGrid.setCurrentPage(reviewPage.getNumber() + 1);
        reviewGrid.setTotalPages(reviewPage.getTotalPages());
        reviewGrid.setTotalRecords(reviewPage.getTotalElements());
        reviewGrid.setReviewData(Lists.newArrayList(reviewPage.iterator()));

        return reviewGrid;
    }
}
