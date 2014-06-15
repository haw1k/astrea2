package ru.astrea.logic.controller.main;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import ru.astrea.logic.entity.Review;
import ru.astrea.logic.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

@Controller("rightMenuController")
@Scope("session")
public class RightMenuController extends ViewPreparerSupport {

    @Autowired
    private ReviewService reviewService;

    @Override
    public void execute(TilesRequestContext tilesContext,
                        AttributeContext attributeContext) {
        List<Review> reviews = reviewService.findAll();
        List<Review> forRightMenuReviews = new ArrayList<Review>();
        for(int i = reviews.size() - 1; i > reviews.size() - 4; i--) {
            Review review = reviews.get(i);
            review.setReview(review.getReview().substring(0, 200));
            forRightMenuReviews.add(review);
        }
        tilesContext.getRequestScope().put("reviews", forRightMenuReviews);
    }
}