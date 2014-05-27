package ru.astrea.logic.controller.form;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = PhoneConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "{Phone}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
