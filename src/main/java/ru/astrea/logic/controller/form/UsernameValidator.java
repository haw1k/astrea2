package ru.astrea.logic.controller.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.astrea.logic.entity.User;
import ru.astrea.logic.service.UserService;

public class UsernameValidator implements Validator {
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;

        if(userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "valid.username.Unique");
        }
    }
}
