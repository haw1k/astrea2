package ru.astrea.logic.service.test;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.astrea.logic.entity.Turn;
import ru.astrea.logic.entity.TurnCategory;
import ru.astrea.logic.service.TurnCategoryService;
import ru.astrea.logic.service.jpa.TurnCategoryServiceImpl;

import java.util.List;

public class TurnCategoryServiceTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("/resources/config/root-context.xml");
        ctx.refresh();

        TurnCategoryService turnCategoryService = ctx.getBean("TurnCategoryServiceImpl", TurnCategoryServiceImpl.class);

        //Поиск категории по id
        int id = 1;
        TurnCategory catById = turnCategoryService.findById(id);
        System.out.println("Найденная по id = " + id + " категория услуг");
        System.out.println("------------------------------------");
        System.out.println(catById);
        System.out.println("------------------------------------");
        System.out.println("И услуги соответствующие этим категориям :");
        for (Turn turn : catById.getTurns()) {
            System.out.println("------------------------------------");
            System.out.println(turn);
            System.out.println("------------------------------------");
        }

        //Поиск всех категорий
        List<TurnCategory> allCats = turnCategoryService.findAll();
        System.out.println("Все категории услуг :");
        for (TurnCategory cat : allCats) {
            System.out.println("------------------------------------");
            System.out.println(cat);
            System.out.println("------------------------------------");
        }

        //Поиск категории по критерию (для реализации поиска)
        String criterian = "First";
        List<TurnCategory> catsByCriterian = turnCategoryService.findByCriterion(criterian);
        System.out.println("Категории найденные по критерию :");
        for (TurnCategory cat : catsByCriterian) {
            System.out.println("------------------------------------");
            System.out.println(cat);
            System.out.println("------------------------------------");
        }

        //Обновление категории
        TurnCategory beforeUpdate = turnCategoryService.findById(2);
        System.out.println("Категория до обновления");
        System.out.println("------------------------------------");
        System.out.println(beforeUpdate);
        System.out.println("------------------------------------");

        beforeUpdate.setTitle("BUGAGA");
        turnCategoryService.editCategory(beforeUpdate);

        TurnCategory afterUpdate = turnCategoryService.findById(2);
        System.out.println("Категория после обновления");
        System.out.println("------------------------------------");
        System.out.println(afterUpdate);
        System.out.println("------------------------------------");

    }
}
