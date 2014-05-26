package ru.astrea.logic.controller.main;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import ru.astrea.logic.entity.TurnCategory;
import ru.astrea.logic.service.TurnCategoryService;

import java.util.List;

@Controller("leftMenuController")
@Scope("session")
public class LeftMenuController extends ViewPreparerSupport {

    @Autowired
    private TurnCategoryService turnCategoryServiceService;

    @Override
    public void execute(TilesRequestContext tilesContext,
                        AttributeContext attributeContext) {
        List<TurnCategory> categories = turnCategoryServiceService.findAll();
        tilesContext.getRequestScope().put("categories", categories);
    }
}