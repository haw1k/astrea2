package ru.astrea.logic.controller.main;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import ru.astrea.logic.entity.Other;
import ru.astrea.logic.service.OtherService;

import java.util.List;

@Controller("headerController")
@Scope("session")
public class HeaderController extends ViewPreparerSupport {

    @Autowired
    private OtherService otherService;

    @Override
    public void execute(TilesRequestContext tilesContext,
                        AttributeContext attributeContext) {
        List<Other> all = otherService.findAll();
        Other other = all.get(0);
        tilesContext.getRequestScope().put("other", other);
    }
}