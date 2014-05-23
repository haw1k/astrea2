package ru.astrea.logic.controller.form;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.astrea.logic.entity.TurnCategory;

public class ImgValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return TurnCategory.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TurnCategory turnCategory = (TurnCategory)o;

        if(turnCategory.getImg() == null) {
            errors.rejectValue("img", "valid.turnCategoryImg.NotNull");
        } else {
            if(turnCategory.getImg().length > 100000){
                errors.rejectValue("img", "valid.turnCategoryImg.MaxSize");
            }
        }

        Integer min = 3;
        Integer max = 36;
        if((turnCategory.getTitle().length() < min) || (turnCategory.getTitle().length() > max)) {
            errors.rejectValue("title", "valid.title.Size", new Object[]{min, max}, "");
        }
    }
}
