package ru.astrea.logic.service.test;

import org.joda.time.LocalDate;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.astrea.logic.entity.Turn;
import ru.astrea.logic.entity.TurnCategory;
import ru.astrea.logic.service.TurnCategoryService;
import ru.astrea.logic.service.TurnService;
import ru.astrea.logic.service.jpa.TurnCategoryServiceImpl;
import ru.astrea.logic.service.jpa.TurnServiceImpl;

import java.util.List;

public class TurnServiceTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("/resources/config/root-context.xml");
        ctx.refresh();


        TurnService turnService = ctx.getBean("TurnServiceImpl", TurnServiceImpl.class);
        TurnCategoryService turnCategoryService = ctx.getBean("TurnCategoryServiceImpl", TurnCategoryServiceImpl.class);

        //Поиск категории по id
        Long id = new Long(2);
        Turn turnById = turnService.findById(id);
        System.out.println("Найденная по id = " + id + " услуга");
        System.out.println("------------------------------------");
        System.out.println(turnById);
        System.out.println("------------------------------------");

        //Поиск всех услуг
        List<Turn> allTurns = turnService.findAll();
        System.out.println("Все услуги :");
        for (Turn turn : allTurns) {
            System.out.println("------------------------------------");
            System.out.println(turn);
            System.out.println("------------------------------------");
        }

        //Поиск категории по критерию (для реализации поиска)
        String criterian = "В ЖИЛОЕ";
        List<Turn> turnsByCriterion = turnService.findByCriterion("fas");
        System.out.println("Категории найденные по критерию :");
        for (Turn turn : turnsByCriterion) {
            System.out.println("------------------------------------");
            System.out.println(turn);
            System.out.println("------------------------------------");
        }

        //Добавление услруги
        List<Turn> allTurnsBeforAdd = turnService.findAll();
        System.out.println("Все услуги до добавления :");
        for (Turn turn : allTurnsBeforAdd) {
            System.out.println("------------------------------------");
            System.out.println(turn);
            System.out.println("------------------------------------");
        }

        Turn turnForAdd = new Turn();

        TurnCategory catForAddTurn = turnCategoryService.findById(2);

        turnForAdd.setTitle("MAMA DORAGAYA");
        turnForAdd.setText("MAMA DORAGAYA 9 UEXAL IZ GORODA BUDU JITB U VAS ");
        turnForAdd.setTurnCategory(catForAddTurn);
        LocalDate dateTime = new LocalDate();
        turnForAdd.setCreationDate(dateTime);

        turnService.addTurn(turnForAdd);

        List<Turn> allTurnsAfterAdd = turnService.findAll();
        System.out.println("Все услуги после добавления :");
        for (Turn turn : allTurnsAfterAdd) {
            System.out.println("------------------------------------");
            System.out.println(turn);
            System.out.println("------------------------------------");
        }

        //Удаление услуги

//        List<Turn> allTurnsBeforeDelete = turnService.findAll();
//        System.out.println("Все услуги до удаления :");
//        for (Turn turn : allTurnsBeforeDelete) {
//            System.out.println("------------------------------------");
//            System.out.println(turn);
//            System.out.println("------------------------------------");
//        }
//
//        turnService.deleteTurn(4);
//
//        List<Turn> allTurnsAfterDelete = turnService.findAll();
//        System.out.println("Все услуги после удаления :");
//        for (Turn turn : allTurnsAfterDelete) {
//            System.out.println("------------------------------------");
//            System.out.println(turn);
//            System.out.println("------------------------------------");
//        }

        //Обновление услуги
        Turn turnBeforeUpdate = turnService.findById(new Long(2));
        System.out.println("Усгула до обновления");
        System.out.println("------------------------------------");
        System.out.println(turnBeforeUpdate);
        System.out.println("------------------------------------");

        turnBeforeUpdate.setTitle("BUgfasfasfsaffsaGAGA");
        turnBeforeUpdate.setText("MUBAGUGAGBUGA");
        turnBeforeUpdate.setTurnCategory(catForAddTurn);
        LocalDate dateTime2 = new LocalDate();
        turnBeforeUpdate.setCreationDate(dateTime2);
        turnService.editTurn(turnBeforeUpdate);

        Turn turnAfterUpdate = turnService.findById(new Long(2));
        System.out.println("Услуга после обновления");
        System.out.println("------------------------------------");
        System.out.println(turnAfterUpdate);
        System.out.println("------------------------------------");
    }
}
