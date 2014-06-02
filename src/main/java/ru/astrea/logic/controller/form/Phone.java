package ru.astrea.logic.controller.form;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Target({FIELD, METHOD})
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
